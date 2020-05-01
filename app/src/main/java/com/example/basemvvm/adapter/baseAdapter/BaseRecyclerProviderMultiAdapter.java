package com.example.basemvvm.adapter.baseAdapter;

import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.example.basemvvm.R;
import com.example.basemvvm.base.app.BaseApplication;
import com.example.basemvvm.base.entity.BaseMultiEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 多布局adapter 处理复杂逻辑
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
    protected View getRecyclerEmptyView(){
        View view = LayoutInflater.from(BaseApplication.instance).inflate(R.layout.view_default_recycler_empty,null);
        return view;
    }

    /**
     * 添加Provider类型
     */
    protected abstract void addItemType();

}
