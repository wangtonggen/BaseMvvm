package com.example.basemvvm.mvvm.view_model;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.fragment.BaseMVVMFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;
import com.example.basemvvm.utils.common_utils.LogUtils;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentVM {
    public final ObservableField<String> text = new ObservableField<>();

    public HomeVM(BaseMVVMFragment fragment) {
        super(fragment);
        LogUtils.logE(TAG, "VM");
        text.set("hello word,it is my home");
    }
}
