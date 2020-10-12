package com.wang.mvvmcore.base.baseViewModel;

import android.content.Context;

import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.fragment.BaseFragment;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 fragment
 */
public abstract class BaseFragmentLifecycleVM extends BaseLifecycleVM {
    protected BaseFragment mFragment;
    protected BaseActivity mActivity;
    protected Context mContext;

    public BaseFragmentLifecycleVM(BaseFragment fragment) {
        mDisposables = new CompositeDisposable();
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }

    public BaseFragmentLifecycleVM(BaseFragment fragment, BaseActivity mActivity) {
        mDisposables = new CompositeDisposable();
        this.mFragment = fragment;
        this.mActivity = mActivity;
        this.mContext = this.mFragment.getContext();
    }

    /**
     * 初始化
     */
    protected void init() {

    }
}
