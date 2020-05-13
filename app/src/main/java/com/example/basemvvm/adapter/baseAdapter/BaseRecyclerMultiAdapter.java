package com.example.basemvvm.adapter.baseAdapter;

import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.base.app.BaseApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 适用于类型较少，业务不复杂的场景，便于快速使用。
 */
public abstract class BaseRecyclerMultiAdapter<T extends MultiItemEntity, VH extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, VH> {
    public BaseRecyclerMultiAdapter(@Nullable List<T> data) {
        super(data);
        setEmptyView(getRecyclerEmptyView());
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
        if (vh.getBinding() != null) {
            vh.getBinding().executePendingBindings();
        }
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
     * 设置数据源
     *
     * @param vh viewHolder
     * @param t  数据源
     */
    public abstract void bindData(@NotNull VH vh, T t);

}
