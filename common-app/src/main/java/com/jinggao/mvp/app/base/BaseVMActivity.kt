package com.jinggao.mvp.app.base

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jinggao.mvp.app.ClassUtils


/**
 * @author jing.gao
 * @date 2021/3/9 18:25
 * @version 1.0
 */
open class BaseVMActivity<VM : ViewModel> : AppCompatActivity() {
    // ViewModel
    private lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()

    }


    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        val viewModelClass = ClassUtils.getViewModel(this)
        if (viewModelClass != null) {
            this.viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(viewModelClass as Class<VM>)
        }
    }


    @NonNull
    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this)
    }


}