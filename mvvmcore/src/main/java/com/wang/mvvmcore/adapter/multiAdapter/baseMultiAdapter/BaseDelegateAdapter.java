package com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public abstract class BaseDelegateAdapter<T, VH extends BaseViewHolder> extends BaseDelegateMultiAdapter<T,VH> {
    public BaseDelegateAdapter() {
        setMultiTypeDelegate(getBaseMultiTypeDelegate());
    }

    /**
     * 获取代理
     *
     * @return 代理实体
     */
    public abstract BaseMultiTypeDelegate<T> getBaseMultiTypeDelegate();
}
