package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.baseViewModel.BaseVM;
import com.example.basemvvm.bean.UserBean;

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
