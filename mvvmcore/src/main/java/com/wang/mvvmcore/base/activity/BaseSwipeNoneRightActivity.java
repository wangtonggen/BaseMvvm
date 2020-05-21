package com.wang.mvvmcore.base.activity;


import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeNoneRightActivity<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseMVVMActivity<B, VM> {
    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }
}
