package com.jinggao.mvp.app.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jinggao.mvp.app.ClassUtils

/**
 * @author jing.gao
 * @date 2021/3/10 13:02
 * @version 1.0
 */
open class BaseVMFragment<VM : ViewModel> : Fragment() {
    // ViewModel
    private lateinit var viewModel: VM
    // fragment是否显示了
    private var mIsVisible = false

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }





    private fun onInvisible() {}

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    private fun loadData() {}

    private fun onVisible() {
        loadData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

}