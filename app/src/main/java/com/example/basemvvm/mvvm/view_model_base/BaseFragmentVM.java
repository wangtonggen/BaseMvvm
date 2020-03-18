package com.example.basemvvm.mvvm.view_model_base;

import android.content.Context;

import com.example.basemvvm.base.BaseMvvmFragment;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 fragment
 */
public abstract class BaseFragmentVM extends BaseVM {
    protected BaseMvvmFragment mFragment;
    protected Context mContext;

    public BaseFragmentVM(BaseMvvmFragment fragment) {
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }
}
