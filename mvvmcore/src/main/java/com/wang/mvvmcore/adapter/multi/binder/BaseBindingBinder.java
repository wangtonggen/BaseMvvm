package com.wang.mvvmcore.adapter.multi.binder;

import androidx.viewbinding.ViewBinding;

import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder;
import com.wang.mvvmcore.adapter.multi.adapter.BaseMultiBinderAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/19 0019
 * desc: 使用dataBinding的binder 在使用{@link BaseMultiBinderAdapter} 是必须继承该类
 *
 * @param <T>  bean
 * @param <VB> viewBinding
 */
public abstract class BaseBindingBinder<T, VB extends ViewBinding> extends QuickViewBindingItemBinder<T, VB> {

    @Override
    public void convert(@NotNull BinderVBHolder<VB> holder, T t) {
        VB vb = holder.getViewBinding();
        setData(vb, t);
    }

    public abstract void setData(VB viewDataBinding, T t);
}
