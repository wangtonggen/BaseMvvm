package com.example.basemvvm.adapter.nomvvm;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.MultiItemBean;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiAdapter.BaseMultiAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:
 */
public class MultiNoMvvmAdapter extends BaseMultiAdapter<MultiItemBean, BaseViewHolder> {
    @Override
    public void addItemTypes() {
        addItemType(0, R.layout.recycler_item_multi01);
        addItemType(1, R.layout.recycler_item_multi);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MultiItemBean multiItemBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                baseViewHolder.setText(R.id.tv1, multiItemBean.name);
                break;
            case 1:
                baseViewHolder.setText(R.id.tv, multiItemBean.name);
                break;
        }
    }
}
