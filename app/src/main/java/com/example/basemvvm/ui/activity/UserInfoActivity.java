package com.example.basemvvm.ui.activity;

import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.activity.BaseSwipeBackLeftActivity;
import com.example.basemvvm.base.baseViewModel.BaseToolbarActivityVM;
import com.example.basemvvm.databinding.ActivityUserInfoBinding;
import com.example.basemvvm.mvvm.viewModel.UserInfoVM;
import com.gyf.immersionbar.ImmersionBar;

import jp.wasabeef.glide.transformations.BlurTransformation;

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
        binding.toolbar.setToolbarVM(new BaseToolbarActivityVM(this));
        Glide.with(this).load(R.mipmap.bg_top)
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                .into(binding.ivBgTop);
//        Glide.with(this).asBitmap().apply(RequestOptions.bitmapTransform(new BlurTransformation(this,0))).load(R.mipmap.bg_top).into(binding.ivBgTop);
    }
}
