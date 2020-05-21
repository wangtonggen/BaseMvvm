package com.wang.mvvmcore.base.activity;

import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeBackRightActivity<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseMVVMActivity<B, VM> {
    @Override
    protected int getEdgeTrackingEnabled() {
        return SwipeBackLayout.EDGE_RIGHT;
    }
}
