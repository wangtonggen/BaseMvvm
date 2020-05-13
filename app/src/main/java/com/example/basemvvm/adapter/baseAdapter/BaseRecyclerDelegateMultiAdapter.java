package com.example.basemvvm.adapter.baseAdapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/4/24 0024
 * desc:适用于: 1、实体类不方便扩展，此Adapter的数据类型可以是任意类型，只需要在BaseMultiTypeDelegate.getItemType中返回对应类型
 *             2、item 类型较少 如果类型较多，为了方便隔离各类型的业务逻辑，推荐使用BaseProviderMultiAdapter
 */
public abstract class BaseRecyclerDelegateMultiAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T, VH> {
    public BaseRecyclerDelegateMultiAdapter(@Nullable List<T> data) {
        super(data);
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
        if (vh.getBinding() != null) {
            vh.getBinding().executePendingBindings();
        }
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
