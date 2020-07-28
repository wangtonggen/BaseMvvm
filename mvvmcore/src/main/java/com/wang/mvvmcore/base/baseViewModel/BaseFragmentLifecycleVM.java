package com.wang.mvvmcore.base.baseViewModel;

import android.content.Context;

import com.lxj.xpopup.XPopup;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.fragment.BaseFragment;
import com.wang.mvvmcore.base.fragment.BaseMVVMFragment;

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
        this.mFragment = fragment;
        this.mContext = this.mFragment.getContext();
    }

    public BaseFragmentLifecycleVM(BaseFragment fragment, BaseActivity mActivity) {
        this.mFragment = fragment;
        this.mActivity = mActivity;
        this.mContext = this.mFragment.getContext();
    }

    @Override
    public void showLoadingDialog(String content) {
        if (basePopupView == null){
            basePopupView = new XPopup.Builder(mContext).asLoading(content);
        }
        if (basePopupView != null && !basePopupView.isShow()){
            basePopupView.show();
        }
    }

    /**
     * 初始化
     */
    protected void init() {

    }
}
