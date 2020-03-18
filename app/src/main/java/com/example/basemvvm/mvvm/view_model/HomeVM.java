package com.example.basemvvm.mvvm.view_model;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.BaseMvvmFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 首页的viewModel
 */
public class HomeVM extends BaseFragmentVM {
    public final ObservableField<String> text = new ObservableField<>();
    public HomeVM(BaseMvvmFragment fragment) {
        super(fragment);

        text.set("hello word,it is my home");
    }
}
