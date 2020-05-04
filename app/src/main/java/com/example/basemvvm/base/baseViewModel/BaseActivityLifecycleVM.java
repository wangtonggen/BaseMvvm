package com.example.basemvvm.base.baseViewModel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.example.basemvvm.base.activity.BaseActivity;
import com.lxj.xpopup.XPopup;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 activity
 */
public abstract class BaseActivityLifecycleVM extends BaseLifecycleVM {
    protected BaseActivity mActivity;
    protected Bundle bundle;

    public BaseActivityLifecycleVM(BaseActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void showLoadingDialog(String content) {
        basePopupView = new XPopup.Builder(mActivity).dismissOnTouchOutside(false).dismissOnBackPressed(false).asLoading(TextUtils.isEmpty(content) ? "加载中" : content);
        basePopupView.show();
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
