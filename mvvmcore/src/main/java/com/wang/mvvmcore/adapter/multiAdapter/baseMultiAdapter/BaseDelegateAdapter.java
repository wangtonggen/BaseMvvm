package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:多类型布局，通过代理类的方式，返回布局 id 和 item 类型；
 *   适用于:
 *   1、实体类不方便扩展，此Adapter的数据类型可以是任意类型，只需要在[BaseMultiTypeDelegate.getItemType]中返回对应类型
 *   2、item 类型较少
 *   如果类型较多，为了方便隔离各类型的业务逻辑，推荐使用[BaseMultiBinderAdapter]
 */
public abstract class BaseDelegateAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T,VH> implements LoadMoreModule {
    public BaseDelegateAdapter() {
        setEmptyView(getRecyclerEmptyView());
        setMultiTypeDelegate(getBaseMultiTypeDelegate());
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
     * 获取代理
     *
     * @return 代理实体
     */
    public abstract BaseMultiTypeDelegate<T> getBaseMultiTypeDelegate();
}
