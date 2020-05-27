package com.example.basemvvm.adapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.databinding.RecyclerItemImageBinding;
import com.example.basemvvm.databinding.RecyclerItemUserBinding;
import com.example.basemvvm.mvvm.viewModel.ImageItemVM;
import com.example.basemvvm.mvvm.viewModel.UserItemVM;
import com.wang.mvvmcore.adapter.multiAdapter.baseMultiBindingAdapter.BaseBindingDelegateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class DelegateAdapter extends BaseBindingDelegateAdapter<UserBean, BaseViewHolder> {

    @Override
    public void bindData(@NotNull BaseViewHolder baseViewHolder, UserBean multiItemBean) {
        switch (baseViewHolder.getItemViewType()) {
            case 0:
                RecyclerItemUserBinding recyclerItemUserBinding = DataBindingUtil.getBinding(baseViewHolder.itemView);
                if (recyclerItemUserBinding != null) {
                    recyclerItemUserBinding.setUserItemVM(new UserItemVM(multiItemBean));
                }
                break;
            case 1:
                RecyclerItemImageBinding recyclerItemImageBinding = DataBindingUtil.getBinding(baseViewHolder.itemView);
                if (recyclerItemImageBinding != null) {
                    recyclerItemImageBinding.setImageItemVM(new ImageItemVM(multiItemBean));
                }
                break;
        }
    }

    @Override
    public BaseMultiTypeDelegate<UserBean> getBaseMultiTypeDelegate() {
        return new MyMultiTypeDelegate();
    }

    public static class MyMultiTypeDelegate extends BaseMultiTypeDelegate<UserBean> {
        public MyMultiTypeDelegate() {
            addItemType(0, R.layout.recycler_item_user);
            addItemType(1, R.layout.recycler_item_image);
        }

        @Override
        public int getItemType(@NotNull List<? extends UserBean> list, int i) {
            return list.get(i).getItemType();
        }
    }
}
