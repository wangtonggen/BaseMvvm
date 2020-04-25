package com.example.basemvvm.adapter.baseAdapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.example.basemvvm.base.entity.BaseMultiEntity;

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
    }

    /**
     * 添加Provider类型
     */
    abstract void addItemType();

}
