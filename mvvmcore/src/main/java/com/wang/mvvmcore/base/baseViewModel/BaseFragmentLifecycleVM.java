package com.wang.mvvmcore.base.baseViewModel;

import android.content.Context;
import android.text.TextUtils;

import com.lxj.xpopup.XPopup;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.fragment.BaseFragment;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

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

    @Override
    public void showLoadingDialog(String content) {
        if (loadingPopupView == null) {
            loadingPopupView = new XPopup.Builder(mContext).dismissOnTouchOutside(false).dismissOnBackPressed(false).asLoading();
        }
        if (loadingPopupView.isShow()) {
            return;
        }
        loadingPopupView.setTitle(TextUtils.isEmpty(content) ? "加载中" : content);
        loadingPopupView.show();
    }

    /**
     * 初始化
     */
    protected void init() {

    }
}
