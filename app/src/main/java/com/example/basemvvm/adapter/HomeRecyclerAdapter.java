package com.example.basemvvm.adapter;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.baseAdapter.BaseRecyclerMultiAdapter;
import com.example.basemvvm.bean.MultiItemBean;
import com.example.basemvvm.databinding.RecyclerItemHome01Binding;
import com.example.basemvvm.databinding.RecyclerItemHomeBinding;
import com.example.basemvvm.mvvm.view_model.ItemVM;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 多布局监听
 */
public class HomeRecyclerAdapter extends BaseRecyclerMultiAdapter<MultiItemBean, BaseViewHolder> {
    public HomeRecyclerAdapter(@Nullable List<MultiItemBean> data) {
        super(data);
        addItemType(0, R.layout.recycler_item_home01);
        addItemType(1, R.layout.recycler_item_home);
    }

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, MultiItemBean multiItemBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                RecyclerItemHome01Binding recyclerItemHome01Binding = baseViewHolder.getBinding();
                if (recyclerItemHome01Binding != null) {
                    recyclerItemHome01Binding.setMultiItemBean(multiItemBean);
                    recyclerItemHome01Binding.executePendingBindings();
                }
                break;
            case 1:
                RecyclerItemHomeBinding recyclerItemHomeBinding = baseViewHolder.getBinding();
                if (recyclerItemHomeBinding != null) {
                    recyclerItemHomeBinding.setMultiItemBean(multiItemBean);
                    ItemVM itemVM = new ItemVM();
                    recyclerItemHomeBinding.setItemVM(itemVM);
                    recyclerItemHomeBinding.executePendingBindings();
                }
                break;
        }
    }
}
