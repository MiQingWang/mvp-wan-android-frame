package com.jinggao.mvp.wan.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jinggao.mvp.app.base.BaseVMFragment

import com.jinggao.mvp.wan.R
import com.jinggao.mvp.wan.vm.NavigationViewModel

class NavigationFragment : BaseVMFragment<NavigationViewModel>() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.navigation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
