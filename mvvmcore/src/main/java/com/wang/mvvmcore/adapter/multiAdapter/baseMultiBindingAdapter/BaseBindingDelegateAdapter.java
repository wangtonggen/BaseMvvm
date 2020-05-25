package com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/24 0024
 * desc:适用于: 1、实体类不方便扩展，此Adapter的数据类型可以是任意类型，只需要在BaseMultiTypeDelegate.getItemType中返回对应类型
 * 2、item 类型较少 如果类型较多，为了方便隔离各类型的业务逻辑，推荐使用BaseProviderMultiAdapter
 */
public abstract class BaseBindingDelegateAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T, VH> {
    public BaseBindingDelegateAdapter() {
        setEmptyView(getRecyclerEmptyView());
        setMultiTypeDelegate(getBaseMultiTypeDelegate());
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
    public abstract void bindData(@NotNull VH vh, T t);

    /**
     * 获取代理
     *
     * @return 代理实体
     */
    public abstract BaseMultiTypeDelegate<T> getBaseMultiTypeDelegate();
}
