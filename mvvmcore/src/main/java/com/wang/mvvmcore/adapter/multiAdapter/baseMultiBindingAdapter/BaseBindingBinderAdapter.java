package com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseBinderAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class BaseBindingBinderAdapter extends BaseBinderAdapter implements LoadMoreModule {
    public BaseBindingBinderAdapter() {
        super();
        setEmptyView(getRecyclerEmptyView());
    }

    /**
     * 设置列表无数据时的处理
     *
     * @return view
     */
    @SuppressLint("InflateParams")
    protected View getRecyclerEmptyView() {
        return LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
    }

}
