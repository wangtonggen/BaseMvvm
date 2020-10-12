package com.example.basemvvm.base.binder;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.MultiItemBean;
import com.wang.mvvmcore.adapter.multi.binder.BaseBinder;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:
 */
public class MultiItemBeanNoMvvmBinder extends BaseBinder<MultiItemBean> {
    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_multi;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, MultiItemBean multiItemBean) {
        baseViewHolder.setText(R.id.tv,multiItemBean.name);
    }
}
