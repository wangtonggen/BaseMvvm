package com.example.basemvvm.mvvm.view_model_base;

import android.os.Bundle;
import android.view.View;

import com.example.basemvvm.base.activity.BaseActivity;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: ViewModel的基类 activity
 */
public abstract class BaseActivityVM extends BaseVM {
    protected BaseActivity mActivity;
    protected Bundle bundle;

    public BaseActivityVM(BaseActivity mActivity) {
        this.mActivity = mActivity;
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
