package com.example.basemvvm.base.binder;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/19 0019
 * desc: 绑定data 创建binder
 */
public abstract class BaseViewDataBinder<T, VB extends ViewBinding> extends QuickViewBindingItemBinder<T, VB> {

    @Override
    public void convert(@NotNull BinderVBHolder<VB> holder, T t) {
        VB vb = holder.getViewBinding();
        setData(vb, t);
    }

    public abstract void setData(VB viewDataBinding, T t);
}
