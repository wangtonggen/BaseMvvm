package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc: 多布局 适合不复杂的逻辑
 */
public abstract class BaseMultiAdapter<T extends MultiItemEntity,VH extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,VH> implements LoadMoreModule {
    public BaseMultiAdapter() {
        super();
        addItemTypes();
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
     * 添加item的类型
     */
    public abstract void addItemTypes();
}
