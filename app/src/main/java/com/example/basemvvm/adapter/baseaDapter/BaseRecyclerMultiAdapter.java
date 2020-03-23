package com.example.basemvvm.adapter.baseaDapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 多布局的基类
 */
public abstract class BaseRecyclerMultiAdapter<T extends MultiItemEntity, VH extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, VH> {
    public BaseRecyclerMultiAdapter(@Nullable List<T> data) {
        super(data);
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
        bindData(vh, t);
    }

    /**
     * 设置数据源
     * @param vh viewHolder
     * @param t 数据源
     */
    public abstract void bindData(@NotNull VH vh, T t);

}
