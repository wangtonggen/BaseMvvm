package com.example.basemvvm.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.basemvvm.base.fragment.BaseFragment;

import java.util.List;

/**
 * author：wtg
 * time：2020/5/1
 * desc：
 */
public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<BaseFragment> baseFragments;
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity,List<BaseFragment> baseFragments) {
        super(fragmentActivity);
        this.baseFragments = baseFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return baseFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return baseFragments.size();
    }
}
