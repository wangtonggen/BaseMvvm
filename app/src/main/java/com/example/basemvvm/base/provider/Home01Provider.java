package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.databinding.RecyclerItemHome01Binding;
import com.example.basemvvm.databinding.RecyclerItemHomeBinding;
import com.example.basemvvm.mvvm.viewModel.ItemVM;
import com.wang.mvvmcore.adapter.multi.provider.BaseBindingProvider;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * time: 2020/10/8
 * desc:
 */
public class Home01Provider extends BaseBindingProvider<MultiItemBean, RecyclerItemHome01Binding> {
    @Override
    protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemHome01Binding viewDataBinding, MultiItemBean multiItemBean) {
        viewDataBinding.setMultiItemBean(multiItemBean);
    }

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_home01;
    }
}
