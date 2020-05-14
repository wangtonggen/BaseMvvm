package com.example.basemvvm.mvvm.viewModel;

import androidx.databinding.ObservableField;

import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseActivity;
import com.example.basemvvm.base.baseViewModel.BaseActivityLifecycleVM;
import com.example.basemvvm.base.baseViewModel.BaseToolbarVM;
import com.example.basemvvm.utils.common.MyUserSPUtils;

/**
 * author: wtg
 * date:2020/5/7 0007
 * desc:
 */
public class UserInfoVM extends BaseActivityLifecycleVM {
    public ObservableField<Object> topBgUrl = new ObservableField<>();
    public ObservableField<String> userHead = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();
    public BaseToolbarVM baseToolbarVM;

    public UserInfoVM(BaseActivity mActivity) {
        super(mActivity);

        baseToolbarVM = new BaseToolbarVM(mActivity);
        baseToolbarVM.title.set(MyUserSPUtils.getUserName());
//        baseToolbarVM.toolbarAlpha.set(0);
//        baseToolbarVM.titleBgAlpha.set(0);

        topBgUrl.set(R.mipmap.bg_top);
        userHead.set(MyUserSPUtils.getHeadUrl());
        userName.set(MyUserSPUtils.getUserName());
    }
}
