package com.example.basemvvm.base;


import androidx.databinding.ViewDataBinding;

/**
 * author: wtg
 * date:2020/3/12 0012
 * desc: 向左划出
 */
public abstract class BaseSwipeNoneRightActivity<B extends ViewDataBinding> extends BaseMvvmActivity {
    @Override
    protected int getEdgeTrackingEnabled() {
        return -1;
    }
}
