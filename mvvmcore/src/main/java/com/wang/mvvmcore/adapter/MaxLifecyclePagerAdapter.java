package com.wang.mvvmcore.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/17 0017
 * desc: viewPager 配合 LazyFragment 实现懒加载
 */
public class MaxLifecyclePagerAdapter extends FragmentPagerAdapter {
    private List<BaseMVVMFragment> fragments;
    private List<String> titles;

    public MaxLifecyclePagerAdapter(@NonNull FragmentManager fm, List<BaseMVVMFragment> fragments, List<String> titles) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
