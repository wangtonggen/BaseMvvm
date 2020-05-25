package com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter;

import android.view.View;

import com.chad.library.adapter.base.BaseBinderAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.wang.mvvmcore.config.EmptyConfig;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class BaseBindingBinderAdapter extends BaseBinderAdapter implements LoadMoreModule {
    public BaseBindingBinderAdapter() {
        setEmptyView(getRecyclerEmptyView());
    }

    /**
     * 设置列表无数据时的处理
     *
     * @return view
     */
    protected View getRecyclerEmptyView() {
        return EmptyConfig.getInstance().getEmptyView();
    }

}
