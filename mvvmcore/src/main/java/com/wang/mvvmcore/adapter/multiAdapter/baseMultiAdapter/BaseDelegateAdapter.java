package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public abstract class BaseDelegateAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T,VH> {
    public BaseDelegateAdapter() {
        setEmptyView(getRecyclerEmptyView());
        setMultiTypeDelegate(getBaseMultiTypeDelegate());
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

    /**
     * 获取代理
     *
     * @return 代理实体
     */
    public abstract BaseMultiTypeDelegate<T> getBaseMultiTypeDelegate();
}
