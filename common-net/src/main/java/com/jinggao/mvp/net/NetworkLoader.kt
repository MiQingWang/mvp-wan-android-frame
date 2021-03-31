package com.jinggao.mvp.net

import android.app.Application
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.ArrayList
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author jing.gao
 * @date 2021/3/8 17:38
 * @version 1.0
 */

class NetworkLoader() {
    companion object{
        @JvmStatic
        val TAG = NetworkLoader::class.java.simpleName
        //缓存最大容量
        @JvmStatic
        val CACHE_MAX_SIZE = 10 * 1024 * 1024

        }
    class Builder(application: Application) {
        private val networkConfig: NetworkConfig

        init {
            networkConfig = NetworkConfig()
            //设置默认配置
            networkConfig.application = application
            networkConfig.logLevel = HttpLoggingInterceptor.Level.NONE
            networkConfig.isOpenCache = false
            networkConfig.isOpenRxCache = false
        }

        fun setBaseUrl(releaseBaseUrl: String, debugBaseUrl: String): Builder {
            networkConfig.releaseBaseUrl = releaseBaseUrl
            networkConfig.debugBaseUrl = debugBaseUrl
            return this
        }

        fun setLogLevel(logLevel: HttpLoggingInterceptor.Level): Builder {
            networkConfig.logLevel = logLevel
            return this
        }

        fun setOpenCache(isOpenCache: Boolean): Builder {
            networkConfig.isOpenCache = isOpenCache
            return this
        }

        fun setOpenRxCache(isOpenRxCache: Boolean): Builder {
            networkConfig.isOpenRxCache = isOpenRxCache
            return this
        }

        fun setNetworkHeaderParams(networkHeaderParams: NetworkHeaderParams): Builder {
            if (networkConfig.networkHeaderParams == null) {
                networkConfig.networkHeaderParams = mutableListOf(networkHeaderParams)
            } else {
                networkConfig.networkHeaderParams!!.toMutableList().add(networkHeaderParams)
            }
            return this
        }

        fun setOkHttpClientBuild(okHttpClientBuild: OkHttpClient.Builder): Builder {
            networkConfig.okHttpClientBuild = okHttpClientBuild
            return this
        }

        fun build(): Retrofit {
            return Network.retrofit(networkConfig)
        }
    }
}