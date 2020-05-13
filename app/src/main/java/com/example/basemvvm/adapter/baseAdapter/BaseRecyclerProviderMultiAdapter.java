package com.example.basemvvm.adapter.baseAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.example.basemvvm.R;
import com.example.basemvvm.base.app.BaseApplication;
import com.example.basemvvm.base.entity.BaseMultiEntity;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 说明：当有多种条目的时候，避免在convert()中做太多的业务逻辑，把逻辑放在对应的 ItemProvider 中。以及最大化自定义VH类型。
 *      1、此Adapter的数据类型可以是任意类型，只需要在getItemType中返回对应类型
 *      2、Adapter不限定ViewHolder类型。ViewHolder 由 BaseItemProvider 实现，并且每个BaseItemProvider可以拥有自己类型的ViewHolder类型。
 */
public abstract class BaseRecyclerProviderMultiAdapter<T extends BaseMultiEntity> extends BaseProviderMultiAdapter<T> {
    public BaseRecyclerProviderMultiAdapter(@Nullable List<T> data) {
        super(data);
        addItemType();
        setEmptyView(getRecyclerEmptyView());
    }

    /**
     * 设置列表无数据时的处理
     * @return view
     */
    @SuppressLint("InflateParams")
    public View getRecyclerEmptyView(){
        return LayoutInflater.from(BaseApplication.instance).inflate(R.layout.view_default_recycler_empty,null);
    }

    /**
     * 添加Provider类型
     */
    protected abstract void addItemType();

}
