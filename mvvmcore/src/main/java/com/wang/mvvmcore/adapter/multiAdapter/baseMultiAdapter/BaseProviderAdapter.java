package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.adapter.entity.BaseMultiEntity;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public abstract class BaseProviderAdapter<T extends BaseMultiEntity> extends BaseProviderMultiAdapter<T> {
    public BaseProviderAdapter() {
        addItemType();
        setEmptyView(getRecyclerEmptyView());
    }

    @Override
    protected int getItemType(@NotNull List<? extends T> list, int i) {
        return list.get(i).getItemType();
    }
    /**
     * 设置列表无数据时的处理 如果不满意则重写此方法
     *
     * @return view
     */
    @SuppressLint("InflateParams")
    protected View getRecyclerEmptyView() {
        return LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
    }
    /**
     * 添加Provider类型
     */
    protected abstract void addItemType();
}
