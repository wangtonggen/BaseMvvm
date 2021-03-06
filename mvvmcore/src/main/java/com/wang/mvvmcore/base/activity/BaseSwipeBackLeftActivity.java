package com.wang.mvvmcore.base.activity;

import androidx.databinding.ViewDataBinding;

import com.wang.mvvmcore.base.baseViewModel.BaseLifecycleVM;
import com.wang.mvvmcore.constant.SwipeConstant;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向右划出的activity基类
 */
public abstract class BaseSwipeBackLeftActivity<B extends ViewDataBinding, VM extends BaseLifecycleVM> extends BaseMVVMActivity<B, VM> {
    @Override
    protected int getEdgeTrackingEnabled() {
        return SwipeConstant.SWIPE_LEFT;
    }
}
