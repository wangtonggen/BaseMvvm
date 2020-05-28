package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.wang.mvvmcore.R;
import com.wang.mvvmcore.base.app.BaseCoreApplication;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc: 多类型布局，适用于类型较少，业务不复杂的场景，便于快速使用。
 *   data[T]必须实现[MultiItemEntity]
 *   如果数据类无法实现[MultiItemEntity]，请使用[BaseDelegateAdapter]
 *   如果类型较多，为了方便隔离各类型的业务逻辑，推荐使用[BaseProviderAdapter]
 *   T 实体类
 *   VH ViewHolder
 */
public abstract class BaseMultiAdapter<T extends MultiItemEntity,VH extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,VH> implements LoadMoreModule {
    public BaseMultiAdapter() {
        super();
        addItemTypes();
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
     * 添加item的类型
     */
    public abstract void addItemTypes();
}
