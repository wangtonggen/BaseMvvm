package com.example.basemvvm.base.baseViewModel;

import android.content.Context;

import com.example.basemvvm.base.fragment.BaseMVVMFragment;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 fragment
 */
public abstract class BaseFragmentLifecycleVM extends BaseLifecycleVM {
    protected BaseMVVMFragment mFragment;
    protected Context mContext;

    public BaseFragmentLifecycleVM(BaseMVVMFragment fragment) {
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }

    /**
     * 初始化
     */
    protected void init() {
    }
}
