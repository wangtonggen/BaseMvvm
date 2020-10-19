package com.wang.mvvmcore.adapter.singleAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 单布局的基类(数据绑定)
 *
 * @param <T>  数据bean
 * @param <VB> 数据绑定类
 */
public abstract class BaseBindingSingleAdapter<T, VB extends ViewDataBinding> extends BaseQuickAdapter<T, BaseDataBindingHolder<VB>> implements LoadMoreModule {
    public BaseBindingSingleAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        setEmptyView(getRecyclerEmptyView());
    }

    public BaseBindingSingleAdapter(int layoutResId) {
        super(layoutResId);
        setEmptyView(getRecyclerEmptyView());
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<VB> vbBaseDataBindingHolder, T t) {
        if (t == null) {
            return;
        }
        VB viewDataBinding = vbBaseDataBindingHolder.getDataBinding();
        if (viewDataBinding != null) {
            bindData(vbBaseDataBindingHolder, viewDataBinding, t);
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
        return LayoutInflater.from(BaseCoreApplication.instance).inflate(R.layout.view_default_recycler_empty, null);
    }

    /**
     * 设置数据源
     *
     * @param vh viewHolder
     * @param t  数据源
     */
    public abstract void bindData(@NotNull BaseDataBindingHolder<VB> vh, @NotNull VB viewDataBinding, T t);

}
