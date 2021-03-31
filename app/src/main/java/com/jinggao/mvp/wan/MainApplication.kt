package com.jinggao.mvp.wan

import android.app.Application
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.app.SkinAppCompatViewInflater
import skin.support.SkinCompatManager
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.jinggao.mvp.net.NetworkLoader
import me.jessyan.autosize.AutoSizeConfig
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatDelegate


/**
 * @author jing.gao
 * @date 2021/3/9 15:43
 * @version 1.0
 */
class MainApplication :Application(){

    companion object{

        private lateinit var retrofit: Retrofit
        private lateinit var application: MainApplication

        @JvmStatic
        fun getInstance(): MainApplication {
            return application
        }

        @JvmStatic
        fun getRetrofit(): Retrofit {
            return retrofit
        }

    }


    override fun onCreate() {
        super.onCreate()
        application = this
        SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())           // 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
            .addInflater(SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
            .addInflater(SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
            .loadSkin()
        SkinCompatManager.getInstance().restoreDefaultTheme();

        retrofit=  NetworkLoader.Builder(this)
            .setBaseUrl("https://www.wanandroid.com/","https://www.wanandroid.com/")
            .setLogLevel(logLevel = HttpLoggingInterceptor.Level.BODY)
            .build();
    }


}