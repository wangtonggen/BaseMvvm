package com.wang.mvvmcore.adapter.multi.provider;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.adapter.entity.BaseMultiEntity;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 多布局的provider使用dataBinding
 *
 * @param <T>  bean
 * @param <VB> viewBinding
 */
public abstract class BaseBindingProvider<T extends BaseMultiEntity, VB extends ViewDataBinding> extends BaseItemProvider<T> {
    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, T t) {
        if (t == null) {
            return;
        }
        VB viewDataBinding = DataBindingUtil.getBinding(baseViewHolder.itemView);
        if (viewDataBinding != null) {
            bindData(baseViewHolder, viewDataBinding, t);
            viewDataBinding.executePendingBindings();
        }
    }

    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, T data, int position) {
        super.onClick(helper, view, data, position);
    }

    @Override
    public boolean onLongClick(@NotNull BaseViewHolder helper, @NotNull View view, T data, int position) {
        return super.onLongClick(helper, view, data, position);
    }

    /**
     * 设置数据源
     *
     * @param viewHolder viewHolder
     * @param t          数据源
     */
    protected abstract void bindData(@NotNull BaseViewHolder viewHolder, @NotNull VB viewDataBinding, T t);
}
