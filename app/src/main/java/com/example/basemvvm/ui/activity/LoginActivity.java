package com.example.basemvvm.ui.activity;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.content.ContextCompat;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.databinding.ActivityLoginBinding;
import com.example.basemvvm.mvvm.viewModel.LoginVM;
import com.gyf.immersionbar.ImmersionBar;
import com.wang.mvvmcore.base.activity.BaseSwipeBackLeftActivity;
import com.wang.mvvmcore.base.baseViewModel.BaseToolbarVM;


public class LoginActivity extends BaseSwipeBackLeftActivity<ActivityLoginBinding, LoginVM> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginVM getViewModel() {
        return new LoginVM(this);
    }

    @Override
    protected void bindOtherViewModel() {
        BaseToolbarVM baseToolbarVM = new BaseToolbarVM(this);
        baseToolbarVM.title.set("登录");
        baseToolbarVM.toolbarColor.set(R.color.white);
        baseToolbarVM.titleColor.set(R.color.color_title);
        baseToolbarVM.backNavigationResId.set(R.drawable.ic_arrow_back);
        binding.toolbar.setToolbarVM(baseToolbarVM);

    }

    @Override
    protected void initView() {
        ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init();
    }

    @Override
    protected int getViewModelId() {
        return BR.loginVM;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void starAnimation(View view, int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), 0, view.getWidth() / 2.0f);
        view.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, color));
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    private void closeAnimation(View view, final int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), view.getWidth() / 2.0f, 0);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                starAnimation(view, color);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
