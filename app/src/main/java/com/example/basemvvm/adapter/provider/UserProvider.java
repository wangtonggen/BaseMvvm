package com.example.basemvvm.adapter.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserRecyclerAdapter;
import com.example.basemvvm.base.provider.BaseProvider;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.databinding.RecyclerItemUserBinding;
import com.example.basemvvm.mvvm.viewModel.UserItemVM;
import com.example.basemvvm.utils.common.LogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserProvider extends BaseProvider<RecyclerItemUserBinding, UserBean> {

    @Override
    public int getItemViewType() {
        return UserRecyclerAdapter.TYPE_USER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_user;
    }

    @Override
    protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemUserBinding viewDataBinding, UserBean userBean) {
        LogUtils.logE("bindData");
        viewDataBinding.setUserItemVM(new UserItemVM(userBean));
    }
}
