package com.example.basemvvm.ui;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.content.ContextCompat;

import com.example.basemvvm.BR;
import com.example.basemvvm.R;
import com.example.basemvvm.base.BaseSwipeNoneRightActivity;
import com.example.basemvvm.databinding.ActivityMainBinding;
import com.example.basemvvm.mvvm.view_model.LoginViewModel;
import com.example.basemvvm.mvvm.view_model_base.BaseViewModel;


public class MainActivity extends BaseSwipeNoneRightActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return new LoginViewModel(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getViewModelId() {
        return BR.loginVm;
    }

    private void starAnimation(View view, int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), 0, view.getWidth() / 2.0f);
        view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, color));
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
