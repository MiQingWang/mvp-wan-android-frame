package com.jinggao.mvp.net

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author jing.gao
 * @date 2021/3/5 17:18
 * @version 1.0
 */
class NetworkConfig {
     var releaseBaseUrl: String? = null // release地址，根据工具编译的模式自动切换
     var debugBaseUrl: String? = null   // debug地址，根据工具编译的模式自动切换
     var application: Application? = null // Android 全局应用程序主类
     var logLevel: HttpLoggingInterceptor.Level? = null  //网络请求日志打印级别
     var isOpenCache: Boolean? = null  //是否开启网络缓存，无网络时自动返回缓存内容
     var isOpenRxCache: Boolean? = null // 是否开启RxCache数据缓存
     var networkHeaderParams: List<NetworkHeaderParams>? = null  // 自定义配置网络请求header信息
     var okHttpClientBuild: OkHttpClient.Builder? = null //自定义配置OkhttpClient
}