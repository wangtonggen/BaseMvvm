package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;
import com.example.basemvvm.base.baseViewModel.BaseActivityLifecycleVM;
import com.example.basemvvm.base.baseViewModel.BaseToolbarActivityVM;

/**
 * author: wtg
 * date:2020/5/7 0007
 * desc:
 */
public class UserInfoVM extends BaseToolbarActivityVM {
    public ObservableField<String> topBgUrl = new ObservableField<>();
    public UserInfoVM(BaseActivity mActivity) {
        super(mActivity);
    }
}
