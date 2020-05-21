package com.wang.mvvmcore.base.fragment;

import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;

/**
 * author: wtg
 * date:2020/3/17 0017
 * desc: 懒加载
 */
public abstract class LazyLoadFragment<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseMVVMFragment<B, VM> {
    private boolean isFirstLoad = true;//是否是第一次加载 true是 false 否

    @Override
    public void onResume() {
        super.onResume();
        //懒加载 加载数据
        if (isFirstLoad) {
            isFirstLoad = false;
            onLazyLoadData();
        }
    }

    //加载数据
    public abstract void onLazyLoadData();
}
