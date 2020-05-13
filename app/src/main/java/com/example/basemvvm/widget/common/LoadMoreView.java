package com.example.basemvvm.widget.common;

import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.loadmore.BaseLoadMoreView;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/13 0013
 * desc: 加载更多的view
 */
public class LoadMoreView extends BaseLoadMoreView {
    @NotNull
    @Override
    public View getLoadComplete(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getLoadEndView(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getLoadFailView(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getLoadingView(@NotNull BaseViewHolder baseViewHolder) {
        return null;
    }

    @NotNull
    @Override
    public View getRootView(@NotNull ViewGroup viewGroup) {
        return null;
    }
}
