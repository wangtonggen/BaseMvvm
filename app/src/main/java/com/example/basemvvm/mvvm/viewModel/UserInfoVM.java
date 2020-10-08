package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.utils.common.MyUserSPUtils;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseActivityLifecycleVM;
import com.wang.mvvmcore.base.style.BaseToolbarActivityStyle;

/**
 * author: wtg
 * date:2020/5/7 0007
 * desc:
 */
public class UserInfoVM extends BaseActivityLifecycleVM {
    public ObservableField<Object> topBgUrl = new ObservableField<>();
    public ObservableField<String> userHead = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public BaseToolbarActivityStyle baseToolbarActivityStyle;

    public UserInfoVM(BaseActivity mActivity) {
        super(mActivity);

        baseToolbarActivityStyle = new BaseToolbarActivityStyle(mActivity);
        baseToolbarActivityStyle.title.set(MyUserSPUtils.getUserName());
//        baseToolbarVM.toolbarAlpha.set(0);
//        baseToolbarVM.titleBgAlpha.set(0);

        userHead.set(MyUserSPUtils.getHeadUrl());
        userName.set(MyUserSPUtils.getUserName());
    }
}
