package com.example.basemvvm.base.provider;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.basemvvm.R;
import com.example.basemvvm.adapter.UserAdapter;
import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.databinding.RecyclerItemUserBinding;
import com.example.basemvvm.mvvm.viewModel.UserItemVM;
import com.wang.mvvmcore.adapter.multi.provider.BaseBindingProvider;
import com.wang.mvvmcore.utils.common.CoreLogUtils;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserBindingProvider extends BaseBindingProvider<UserBean,RecyclerItemUserBinding> {

    @Override
    public int getItemViewType() {
        return UserAdapter.TYPE_USER;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_item_user;
    }

    @Override
    protected void bindData(@NotNull BaseViewHolder viewHolder, @NotNull RecyclerItemUserBinding viewDataBinding, UserBean userBean) {
        CoreLogUtils.logE("bindData");
        viewDataBinding.setUserItemVM(new UserItemVM(userBean));
    }
}
