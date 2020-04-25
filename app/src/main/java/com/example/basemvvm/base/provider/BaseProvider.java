package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.base.entity.BaseMultiEntity;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 多布局的provider
 */
public abstract class BaseProvider<T extends BaseMultiEntity> extends BaseItemProvider<T> {
    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, T t) {
        if (t == null) {
            return;
        }
        bindData(baseViewHolder, t);
        if (baseViewHolder.getBinding() != null) {
            baseViewHolder.getBinding().executePendingBindings();
        }
    }

    /**
     * 设置数据源
     *
     * @param viewHolder viewHolder
     * @param t          数据源
     */
    abstract void bindData(@NotNull BaseViewHolder viewHolder, T t);
}
