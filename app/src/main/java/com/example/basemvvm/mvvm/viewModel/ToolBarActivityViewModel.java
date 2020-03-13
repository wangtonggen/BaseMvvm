package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.base.BaseActivity;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public abstract class ToolBarActivityViewModel extends BaseActivityViewModel {
    public final ObservableField<String> title = new ObservableField<>();//头部title
    public final ObservableField<String> rightText = new ObservableField<>();//右边文本
    public ToolBarActivityViewModel(BaseActivity mActivity) {
        super(mActivity);
    }
}
