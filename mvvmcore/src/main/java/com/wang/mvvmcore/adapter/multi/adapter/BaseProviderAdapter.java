package com.wang.mvvmcore.adapter.multi.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.adapter.entity.BaseMultiEntity;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 说明：当有多种条目的时候，避免在convert()中做太多的业务逻辑，把逻辑放在对应的 ItemProvider 中。以及最大化自定义VH类型。
 * 1、此Adapter的数据类型可以是任意类型，只需要在getItemType中返回对应类型
 * 2、Adapter不限定ViewHolder类型。ViewHolder 由 {@link com.chad.library.adapter.base.provider.BaseItemProvider} 实现，并且每个BaseItemProvider可以拥有自己类型的ViewHolder类型。
 *
 * @param <T> bean
 */
public abstract class BaseProviderAdapter<T extends BaseMultiEntity> extends BaseProviderMultiAdapter<T> implements LoadMoreModule {
    public BaseProviderAdapter() {
        addItemTypeProvider();
        setEmptyView(getRecyclerEmptyView());
    }

    @Override
    protected int getItemType(@NotNull List<? extends T> list, int i) {
        return list.get(i).getItemType();
    }

    /**
     * 设置列表无数据时的处理
     *
     * @return view
     */
    @SuppressLint("InflateParams")
    public View getRecyclerEmptyView() {
        return LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
    }

    /**
     * 添加Provider类型
     */
    protected abstract void addItemTypeProvider();

}
