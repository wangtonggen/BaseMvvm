package com.example.basemvvm.adapter.baseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 单布局的基类
 */
public abstract class BaseRecyclerSingleAdapter<VB extends ViewDataBinding, T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> {
    public BaseRecyclerSingleAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerSingleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);//绑定数据
    }

    @Override
    protected void convert(@NotNull VH vh, T t) {
        if (t == null) {
            return;
        }

        VB viewDataBinding = getViewDataBinding(vh);
        if (viewDataBinding != null) {
            bindData(vh, viewDataBinding, t);
            viewDataBinding.executePendingBindings();
        }
    }

    /**
     * 获取DataBinding 实例
     *
     * @param vh viewHolder
     * @return DataBinding
     */
    protected VB getViewDataBinding(@NotNull VH vh) {
        return vh.getBinding();
    }

    /**
     * 设置数据源
     *
     * @param vh viewHolder
     * @param t  数据源
     */
    public abstract void bindData(@NotNull VH vh, @NotNull VB viewDataBinding, T t);

}
