package com.wang.mvvmcore.adapter.singleAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc: 不适用binding 的单布局adapter基类
 */
public abstract class BaseSingleAdapter<T,VH extends BaseViewHolder> extends BaseQuickAdapter<T,VH> implements LoadMoreModule {
    public BaseSingleAdapter(int layoutResId) {
        super(layoutResId);
    }
}
