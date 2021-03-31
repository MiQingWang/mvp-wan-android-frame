package com.jinggao.mvp.wan.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.jinggao.mvp.app.base.BaseVMFragment

import com.jinggao.mvp.wan.R
import com.jinggao.mvp.wan.vm.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import skin.support.SkinCompatManager
import skin.support.SkinCompatManager.SkinLoaderListener

class HomeFragment : BaseVMFragment<HomeViewModel>(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false).apply {
            ButterKnife.bind(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}
