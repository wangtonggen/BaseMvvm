package com.example.basemvvm.adapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.databinding.RecyclerItemHome01Binding;
import com.example.basemvvm.databinding.RecyclerItemHomeBinding;
import com.example.basemvvm.mvvm.viewModel.ItemVM;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter.BaseBindingAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 多布局监听
 */
public class HomeBindingAdapter extends BaseBindingAdapter<MultiItemBean, BaseViewHolder> implements LoadMoreModule {
    public HomeBindingAdapter() {
        addItemType(0, R.layout.recycler_item_home01);
        addItemType(1, R.layout.recycler_item_home);
    }

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, MultiItemBean multiItemBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                RecyclerItemHome01Binding recyclerItemHome01Binding = DataBindingUtil.getBinding(baseViewHolder.itemView);
                if (recyclerItemHome01Binding != null) {
                    recyclerItemHome01Binding.setMultiItemBean(multiItemBean);
                }
                break;
            case 1:
                RecyclerItemHomeBinding recyclerItemHomeBinding = DataBindingUtil.getBinding(baseViewHolder.itemView);
                if (recyclerItemHomeBinding != null) {
                    recyclerItemHomeBinding.setMultiItemBean(multiItemBean);
                    ItemVM itemVM = new ItemVM();
                    recyclerItemHomeBinding.setItemVM(itemVM);
                }
                break;
        }
    }
}