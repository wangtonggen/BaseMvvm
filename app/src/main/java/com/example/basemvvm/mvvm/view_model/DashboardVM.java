package com.example.basemvvm.mvvm.view_model;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.BaseMvvmFragment;
import com.example.basemvvm.mvvm.view_model_base.BaseFragmentVM;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc:
 */
public class DashboardVM extends BaseFragmentVM {
    public final ObservableField<String> text = new ObservableField<>();
    public final ObservableField<String> text1 = new ObservableField<>();
    public final ObservableField<String> text2 = new ObservableField<>();
    public DashboardVM(BaseMvvmFragment fragment) {
        super(fragment);
        text.set("wang");
        text1.set("tong");
        text2.set("gen");
    }
}
