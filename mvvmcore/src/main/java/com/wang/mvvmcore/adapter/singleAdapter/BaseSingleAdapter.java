package com.wang.mvvmcore.adapter.singleAdapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.config.EmptyConfig;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc: 不适用binding 的单布局adapter基类
 */
public abstract class BaseSingleAdapter<T,VH extends BaseViewHolder> extends BaseQuickAdapter<T,VH> implements LoadMoreModule {
    public BaseSingleAdapter(int layoutResId) {
        super(layoutResId);
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
