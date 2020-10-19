package com.wang.mvvmcore.adapter.multi.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseBinderAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc: 使用 Binder 来实现adapter，既可以实现单布局，也能实现多布局
 * 数据实体类也不存继承问题
 * 当有多种条目的时候，避免在convert()中做太多的业务逻辑，把逻辑放在对应的 {@link com.wang.mvvmcore.adapter.multi.binder.BaseBinder] 或者{@link com.wang.mvvmcore.adapter.multi.binder.BaseBindingBinder} 中。
 * 适用于以下情况：
 * 1、实体类不方便扩展，此Adapter的数据类型可以是任意类型，默认情况下不需要实现 getItemType
 * 2、item 类型较多，在convert()中管理起来复杂
 * <p>
 * ViewHolder 由 [BaseBinder/BaseViewDataBinder] 实现，并且每个{@link com.wang.mvvmcore.adapter.multi.binder.BaseBinder] 或者{@link com.wang.mvvmcore.adapter.multi.binder.BaseBindingBinder}可以拥有自己类型的ViewHolder类型。
 */
public class BaseMultiBinderAdapter extends BaseBinderAdapter implements LoadMoreModule {
    public BaseMultiBinderAdapter(@Nullable List<Object> list) {
        super(list);
        setEmptyView(getRecyclerEmptyView());
    }

    public BaseMultiBinderAdapter() {
        super();
        setEmptyView(getRecyclerEmptyView());
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

}
