package com.wang.mvvmcore.bindAdapter;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * author: wtg
 * date:2020/3/17 0017
 * desc: viewPagerAdapter
 */
public class ViewPagerBindingAdapter {

    /**
     * 设置viewPager的监听
     *
     * @param viewPager          viewPager
     * @param pageChangeListener 监听
     */
    @BindingAdapter(value = {"pageChangeListener"}, requireAll = false)
    public static void viewPagerChange(ViewPager viewPager, ViewPager.OnPageChangeListener pageChangeListener) {
        viewPager.addOnPageChangeListener(pageChangeListener);
    }


    public static void setFragmentManager(ViewPager viewPager, FragmentManager fragmentManager) {

    }

    /**
     * 设置adapter
     *
     * @param viewPager    viewPager
     * @param pagerAdapter adapter
     */
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }
}
