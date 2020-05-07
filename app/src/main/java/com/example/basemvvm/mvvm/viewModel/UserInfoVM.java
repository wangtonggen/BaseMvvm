package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;
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
        title.set("王大锤");
        titleColor.set(R.color.white);
        backNavigationResId.set(R.drawable.ic_arrow_back_white);
        backNavigationShow.set(true);
    }
}
