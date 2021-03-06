package com.jinggao.mvp.net

import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @author jing.gao
 * @date 2021/3/9 15:02
 * @version 1.0
 */
class NetworkInterceptor (val networkHeaderParamsList: List<NetworkHeaderParams>?) : Interceptor{
    companion object{
        //    private static final String FOR_NAME = "UTF-8";
        private val TAG = NetworkInterceptor::class.java.simpleName
        val DEFAULT_IS_ADD_HEADER = "true"
    }


    /**
     * 初始化设置外部自定义header信息接口集合
     *
     * @param networkHeaderParamsList 自定义header信息接口集合
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        //框架内部设置外部自定义header头信息
        if (networkHeaderParamsList != null && networkHeaderParamsList.size > 0) {
            for (networkHeaderParams in networkHeaderParamsList) {
                addHeaderParams(request, requestBuilder, networkHeaderParams)
            }
        }
        return chain.proceed(requestBuilder.build())

    }

    /**
     * 封装的设置header头信息方法
     *
     * @param request             HTTP 请求
     * @param networkHeaderParams 自定义header信息接口
     */
    private fun addHeaderParams(
        request: Request,
        requestBuilder: Request.Builder,
        networkHeaderParams: NetworkHeaderParams
    ) {
        val paramStr = request.header(networkHeaderParams.paramsName())
        var isAddHeader = false
        if (!TextUtils.isEmpty(paramStr) && DEFAULT_IS_ADD_HEADER == paramStr) {
            isAddHeader = true
        }
        if (isAddHeader) {
            requestBuilder
                .addHeader(networkHeaderParams.headerName(), networkHeaderParams.headerValue())
                .removeHeader(networkHeaderParams.paramsName())
        } else {
            requestBuilder
                .removeHeader(networkHeaderParams.paramsName())
        }
    }

}