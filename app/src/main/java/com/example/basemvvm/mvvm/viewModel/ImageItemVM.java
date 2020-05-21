package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.base.baseViewModel.BaseVM;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class ImageItemVM extends BaseVM {
    public ObservableField<String> imageUrl = new ObservableField<>();

    public ImageItemVM(UserBean userBean) {
        imageUrl.set(userBean.getUrl());
    }
}
