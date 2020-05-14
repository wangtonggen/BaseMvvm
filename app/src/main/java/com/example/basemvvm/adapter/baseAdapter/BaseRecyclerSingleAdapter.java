package com.example.basemvvm.adapter.baseAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.base.app.BaseApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 单布局的基类
 */
public abstract class BaseRecyclerSingleAdapter<VB extends ViewDataBinding, T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> implements LoadMoreModule {
    public BaseRecyclerSingleAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
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

        VB viewDataBinding = getViewDataBinding(vh);
        if (viewDataBinding != null) {
            bindData(vh, viewDataBinding, t);
            viewDataBinding.executePendingBindings();
        }
    }

    /**
     * 设置列表无数据时的处理
     *
     * @return view
     */
    @SuppressLint("InflateParams")
    protected View getRecyclerEmptyView() {
        return LayoutInflater.from(BaseApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
    }

    /**
     * 获取DataBinding 实例
     *
     * @param vh viewHolder
     * @return DataBinding
     */
    private VB getViewDataBinding(@NotNull VH vh) {
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
