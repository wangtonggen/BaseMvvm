package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.view.View;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.config.EmptyConfig;

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
    protected View getRecyclerEmptyView() {
        return EmptyConfig.getInstance().getEmptyView();
    }

    /**
     * 获取代理
     *
     * @return 代理实体
     */
    public abstract BaseMultiTypeDelegate<T> getBaseMultiTypeDelegate();
}
