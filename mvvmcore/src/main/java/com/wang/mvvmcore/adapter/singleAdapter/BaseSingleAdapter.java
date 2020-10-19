package com.wang.mvvmcore.adapter.singleAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc: 不使用binding 的单布局adapter基类
 *       如果想要修改没有数据的情况下显示的布局只需要重写{@link #getRecyclerEmptyView()}方法即可
 * @param <T> bean
 * @param <VH> viewHolder
 */
public abstract class BaseSingleAdapter<T,VH extends BaseViewHolder> extends BaseQuickAdapter<T,VH> implements LoadMoreModule {
    public BaseSingleAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        setEmptyView(getRecyclerEmptyView());
    }

    public BaseSingleAdapter(int layoutResId) {
        super(layoutResId);
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
