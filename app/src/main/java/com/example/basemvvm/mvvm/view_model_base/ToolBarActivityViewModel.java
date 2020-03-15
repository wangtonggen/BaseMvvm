package com.example.basemvvm.mvvm.view_model_base;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.BaseMvvmActivity;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public abstract class ToolBarActivityViewModel extends BaseActivityViewModel {
    public final ObservableField<String> title = new ObservableField<>();//头部title
    public final ObservableField<String> rightText = new ObservableField<>();//右边文本
    public ToolBarActivityViewModel(BaseMvvmActivity mActivity) {
        super(mActivity);
    }
}
