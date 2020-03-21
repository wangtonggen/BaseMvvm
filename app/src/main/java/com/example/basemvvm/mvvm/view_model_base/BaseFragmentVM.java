package com.example.basemvvm.mvvm.view_model_base;

import android.content.Context;

import com.example.basemvvm.base.fragment.BaseMVVMFragment;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 fragment
 */
public abstract class BaseFragmentVM extends BaseVM {
    protected BaseMVVMFragment mFragment;
    protected Context mContext;

    public BaseFragmentVM(BaseMVVMFragment fragment) {
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }
}
