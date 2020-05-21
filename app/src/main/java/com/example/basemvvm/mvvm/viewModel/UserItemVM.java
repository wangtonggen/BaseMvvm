package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.bean.UserBean;
import com.wang.mvvmcore.base.baseViewModel.BaseVM;
import com.wang.mvvmcore.utils.common.LogUtils;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserItemVM extends BaseVM {
    public ObservableField<String> imageUrl = new ObservableField<>();
    private UserBean userBean;

    public UserItemVM(UserBean userBean) {
        this.userBean = userBean;
        imageUrl.set(userBean.getUrl());

        LogUtils.logE(imageUrl.get() + "---");
    }
}
