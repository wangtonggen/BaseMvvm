package com.example.basemvvm.mvvm.view_model_base;

import android.content.Context;

import com.example.basemvvm.base.BaseFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 fragment
 */
public abstract class BaseFragmentViewModel extends BaseViewModel{
    protected BaseFragment mFragment;
    protected Context mContext;

    public BaseFragmentViewModel(BaseFragment fragment) {
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return mFragment.bindToLifecycle();
    }
}
