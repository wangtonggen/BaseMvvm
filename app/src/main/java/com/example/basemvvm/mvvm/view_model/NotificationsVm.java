package com.example.basemvvm.mvvm.view_model;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.BaseMVVMFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class NotificationsVm extends BaseFragmentVM {
    public ObservableField<String> text = new ObservableField<>();
    public NotificationsVm(BaseMVVMFragment fragment) {
        super(fragment);
        text.set("this is my Notifications");
    }
}
