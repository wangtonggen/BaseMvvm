package com.example.basemvvm.base.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.basemvvm.bean.UserBean;
import com.example.basemvvm.databinding.RecyclerItemImageBinding;
import com.example.basemvvm.mvvm.viewModel.ImageItemVM;
import com.wang.mvvmcore.adapter.multi.binder.BaseBindingBinder;

import org.jetbrains.annotations.NotNull;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc:
 */
public class ImageItemBinder extends BaseBindingBinder<UserBean, RecyclerItemImageBinding> {
    @Override
    public void setData(RecyclerItemImageBinding viewDataBinding, UserBean userBean) {
        viewDataBinding.setImageItemVM(new ImageItemVM(userBean));
    }

    @NotNull
    @Override
    public RecyclerItemImageBinding onCreateViewBinding(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup, int i) {
        return RecyclerItemImageBinding.inflate(layoutInflater,viewGroup,false);
    }
}
