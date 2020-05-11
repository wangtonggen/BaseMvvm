package com.example.basemvvm.ui.activity;

import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import com.blankj.utilcode.util.BarUtils;
import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseSwipeBackLeftActivity;
import com.example.basemvvm.base.baseViewModel.BaseToolbarVM;
import com.example.basemvvm.databinding.ActivityUserInfoBinding;
import com.example.basemvvm.mvvm.viewModel.UserInfoVM;
import com.example.basemvvm.utils.common.LogUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.gyf.immersionbar.ImmersionBar;


/**
 * author: wtg
 * date:2020/5/7 0007
 * desc: 个人信息
 */
public class UserInfoActivity extends BaseSwipeBackLeftActivity<ActivityUserInfoBinding, UserInfoVM> {
    private int height = -1;

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
        Toolbar clToolbar = binding.toolbar.clToolbar;
        clToolbar.post(() -> {
            ViewGroup.LayoutParams layoutParams = clToolbar.getLayoutParams();
            layoutParams.height = clToolbar.getHeight() + BarUtils.getStatusBarHeight();
            clToolbar.setLayoutParams(layoutParams);
            binding.toolbar.clToolbar.setPadding(clToolbar.getPaddingLeft(), BarUtils.getStatusBarHeight(), 0, 0);
        });

        binding.toolbar.setToolbarVM(viewModel.baseToolbarVM);

        binding.appbar.addOnOffsetChangedListener((AppBarLayout.BaseOnOffsetChangedListener) (appBarLayout, verticalOffset) -> {
            if (height == -1) {//完全展开
                height = appBarLayout.getBottom() - clToolbar.getHeight() - BarUtils.getStatusBarHeight();
//                LogUtils.logE("verticalOffset=" + verticalOffset + "---getBottom=" + appBarLayout.getBottom()+"---height="+height+"---"+clToolbar.getHeight()+"---"+BarUtils.getStatusBarHeight());
            }
            if (Math.abs(verticalOffset) <= 0) {
                viewModel.baseToolbarVM.toolbarAlpha.set(0);
                viewModel.baseToolbarVM.titleBgAlpha.set(0);
            } else if (Math.abs(verticalOffset) < height) {
                //获取渐变率
                float scale = (float) Math.abs(verticalOffset) / height;
                //获取渐变数值
                float alpha = (1.0f * scale);
                viewModel.baseToolbarVM.toolbarAlpha.set(alpha);
                viewModel.baseToolbarVM.titleBgAlpha.set(alpha);
            } else {
                viewModel.baseToolbarVM.toolbarAlpha.set(1f);
                viewModel.baseToolbarVM.titleBgAlpha.set(1f);
            }
        });
    }
}
