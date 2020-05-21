package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserBindingAdapter;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.databinding.RecyclerItemImageBinding;
import com.example.basemvvm.mvvm.viewModel.ImageItemVM;
import com.wang.mvvmcore.adapter.provider.BaseProvider;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class ImageProvider extends BaseProvider<RecyclerItemImageBinding, UserBean> {

    @Override
    public int getItemViewType() {
        return UserBindingAdapter.TYPE_IMAGE;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_image;
    }

    @Override
    protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemImageBinding viewDataBinding, UserBean userBean) {
        viewDataBinding.setImageItemVM(new ImageItemVM(userBean));
    }
}
