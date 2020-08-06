package com.wang.mvvmcore.base.baseViewModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lxj.xpopup.XPopup;
import com.wang.mvvmcore.base.activity.BaseActivity;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 activity
 */
public abstract class BaseActivityLifecycleVM extends BaseLifecycleVM {
    protected BaseActivity mActivity;
    protected Bundle bundle;

    public BaseActivityLifecycleVM(BaseActivity mActivity) {
        mDisposables = new CompositeDisposable();
        this.mActivity = mActivity;
    }

    /**
     * 显示加载框 已经再显示了则不做操作
     * 如果加载狂已经存在不用重新创建只改变内容显示
     *
     * @param content 显示内容
     */
    @Override
    public void showLoadingDialog(String content) {
        if (loadingPopupView == null) {
            loadingPopupView = new XPopup.Builder(mActivity).dismissOnTouchOutside(false).dismissOnBackPressed(false).asLoading();
        }
        if (loadingPopupView.isShow()) {
            return;
        }
        loadingPopupView.setTitle(TextUtils.isEmpty(content) ? "加载中" : content);
        loadingPopupView.show();
    }

    /**
     * 初始化数据
     */
    protected void init() {

    }

    /**
     * 设置 bundle 传递数据
     *
     * @param bundle bundle
     */
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    /**
     * 点击返回键 功能
     *
     * @param view view
     */
    public void onBackClick(View view) {
        mActivity.finish();
    }


}
