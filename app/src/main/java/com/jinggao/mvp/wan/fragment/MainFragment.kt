package com.jinggao.mvp.wan.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import butterknife.ButterKnife
import com.jinggao.mvp.app.adapter.ViewPagerAdapter
import com.jinggao.mvp.app.base.BaseVMFragment
import com.jinggao.mvp.wan.R
import com.jinggao.mvp.wan.vm.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*
import skin.support.content.res.SkinCompatResources
import skin.support.widget.SkinCompatSupportable

class MainFragment : BaseVMFragment<MainViewModel>(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {
            ButterKnife.bind(this)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        home_viewpager.adapter = ViewPagerAdapter(childFragmentManager,
            listOf(HomeFragment(), KnowledgeFragment(), NavigationFragment(),ProjectFragment(),MineFragment()))
        home_viewpager.offscreenPageLimit = 3
        initView()
    }

    private fun initView() {
        home_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) = onPageSelectChanged(position)


            override fun onPageScrollStateChanged(state: Int) = Unit
        })
        home_bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            onBottomNavigationSelectChanged(menuItem)
            true
        }
    }


    private fun onPageSelectChanged(index: Int) {
        for (position in 0..index) {
            if (home_bottomNavigationView.visibility == View.VISIBLE)
                home_bottomNavigationView.menu.getItem(position).isChecked = index == position
        }
    }


    private fun onBottomNavigationSelectChanged(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_home -> {
                home_viewpager.currentItem = 0
            }
            R.id.nav_knowledge -> {
                home_viewpager.currentItem = 1
            }
            R.id.nav_navigation -> {
                home_viewpager.currentItem = 2
            }
            R.id.nav_project -> {
                home_viewpager.currentItem = 3
            }
            R.id.nav_mine -> {
                home_viewpager.currentItem = 4
            }
        }
    }


}
