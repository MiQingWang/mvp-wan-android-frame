package com.jinggao.mvp.net

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author jing.gao
 * @date 2021/3/9 15:12
 * @version 1.0
 */
class Utils {

    companion object {

        /**
         * check NetworkAvailable
         * @param context
         * @return
         */
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
                ?: return false
            val info = manager.activeNetworkInfo
            return if (null == info || !info.isAvailable) false else true
        }

    }

}



