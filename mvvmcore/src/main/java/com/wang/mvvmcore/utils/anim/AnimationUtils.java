package com.wang.mvvmcore.utils.anim;

import android.animation.Animator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.content.ContextCompat;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc: 动画的工具类
 */
public class AnimationUtils {

    /**
     * 开始动画
     *
     * @param view  view
     * @param color 颜色值
     */
    private void starAnimation(View view, int color) {
        Animator anim = ViewAnimationUtils.createCircularReveal(view, view.getWidth() / 2, view.getHeight(), 0, view.getWidth() / 2.0f);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), color));
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

    /**
     * 关闭动画
     *
     * @param view  view
     * @param color 颜色值
     */
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
