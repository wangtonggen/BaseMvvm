package com.wang.mvvmcore.adapter.singleAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;
import com.wang.mvvmcore.config.EmptyConfig;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 单布局的基类
 */
public abstract class BaseBindingSingleAdapter<VB extends ViewDataBinding, T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> implements LoadMoreModule {
    public BaseBindingSingleAdapter(int layoutResId) {
        super(layoutResId);
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
    protected View getRecyclerEmptyView() {
        return EmptyConfig.getInstance().getEmptyView();
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
