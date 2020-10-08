package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.databinding.RecyclerItemHomeBinding;
import com.example.basemvvm.mvvm.viewModel.ItemVM;
import com.wang.mvvmcore.adapter.multi.provider.BaseBindingProvider;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * time: 2020/10/8
 * desc:
 */
public class HomeProvider extends BaseBindingProvider<MultiItemBean, RecyclerItemHomeBinding> {
    @Override
    protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemHomeBinding viewDataBinding, MultiItemBean multiItemBean) {
        viewDataBinding.setMultiItemBean(multiItemBean);
        ItemVM itemVM = new ItemVM();
        viewDataBinding.setItemVM(itemVM);
    }

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_home;
    }
}
