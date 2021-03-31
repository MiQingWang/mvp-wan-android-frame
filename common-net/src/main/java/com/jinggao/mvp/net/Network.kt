package com.jinggao.mvp.net

import android.util.Log
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.fastjson.FastJsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @author jing.gao
 * @date 2021/3/9 15:39
 * @version 1.0
 */
object Network {

    fun retrofit(networkConfig : NetworkConfig): Retrofit {
        return getRetrofitInstance(networkConfig)
    }

    private fun getOkHttpClientInstance(networkConfig : NetworkConfig): OkHttpClient {
        //网络请求日志打印拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.e(NetworkLoader.TAG, message) }
        //设置外部配置打印级别
        httpLoggingInterceptor.level=networkConfig.logLevel
        val builder: OkHttpClient.Builder
        //如果外部引用自定义了OkHttpClient.Builder 这里处理合并，否则走框架默认创建方法
        if (networkConfig.okHttpClientBuild != null) {
            builder = networkConfig.okHttpClientBuild!!
        } else {
            builder = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
        }
        //外部引用如果开启缓存，需要设置缓存拦截器，无网络时自动获取缓存数据
        if (networkConfig.isOpenCache!!) {
            val httpCacheDirectory =
                File(networkConfig.application?.cacheDir, "common_net_cache")
            try {
                val cache = Cache(httpCacheDirectory, NetworkLoader.CACHE_MAX_SIZE.toLong())
                builder.cache(cache)
            } catch (e: Exception) {
                Log.e(NetworkLoader.TAG, "Could not create http cache", e)
            }

            builder.addInterceptor(networkConfig.application?.let { NetworkCacheInterceptor(it) })
        }
        return builder
            //框架动态设置header头信息拦截器
            .addInterceptor(NetworkInterceptor(networkConfig.networkHeaderParams))
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }


    private fun getRetrofitInstance(networkConfig : NetworkConfig): Retrofit {
        return Retrofit.Builder()
            .baseUrl(if (BuildConfig.DEBUG) networkConfig.debugBaseUrl else networkConfig.releaseBaseUrl)
            .client(getOkHttpClientInstance(networkConfig))
            //增加返回值为Gson的支持(以实体类返回)
            .addConverterFactory(FastJsonConverterFactory.create())
            //增加返回值为Oservable<T>的支持
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


}