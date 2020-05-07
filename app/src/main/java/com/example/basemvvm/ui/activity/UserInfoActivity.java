package com.example.basemvvm.ui.activity;

import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.BarUtils;
import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseSwipeBackLeftActivity;
import com.example.basemvvm.databinding.ActivityUserInfoBinding;
import com.example.basemvvm.mvvm.viewModel.UserInfoVM;
import com.gyf.immersionbar.ImmersionBar;

/**
 * author: wtg
 * date:2020/5/7 0007
 * desc: 个人信息
 */
public class UserInfoActivity extends BaseSwipeBackLeftActivity<ActivityUserInfoBinding, UserInfoVM> {
    @Override
    protected UserInfoVM getViewModel() {
        return new UserInfoVM(this);
    }

    @Override
    protected int getViewModelId() {
        return BR.userInfoVM;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).init();
        ConstraintLayout clToolbar = binding.toolbar.clToolbar;
        ViewGroup.LayoutParams layoutParams = clToolbar.getLayoutParams();
        layoutParams.height = clToolbar.getMinHeight()+BarUtils.getStatusBarHeight();
        clToolbar.setLayoutParams(layoutParams);
        binding.toolbar.clToolbar.setPadding(clToolbar.getPaddingLeft(), BarUtils.getStatusBarHeight(),0,0);
//        binding.toolbar.clToolbar.getBackground().mutate().setAlpha(0);
    }
}
