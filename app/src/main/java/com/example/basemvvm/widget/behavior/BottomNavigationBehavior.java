package com.example.basemvvm.widget.behavior;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

/**
 * author：wtg
 * time：2020/3/17
 * desc：滑动隐藏底部导航栏
 */
public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<View> {
    private ObjectAnimator outAnimator, inAnimator;
    private boolean isShow = true;
    private boolean isNeedAnimation = true;//是否需要动画

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //垂直滚动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        LogUtils.logE("tag",child+"---"+target);
//        LogUtils.logE("child", consumed[1] + "---" + dy + "---" + target.getScrollY());
//        if (!isNeedAnimation){//如果不需要动画 并且导航栏处于隐藏的情况下则显示导航栏
//            if (!isShow){
//                startShowAnimation(child);
//            }
//            return;
//        }
        if (dy > 0) {// 上滑隐藏
            startHideAnimation(child);
        } else if (dy < 0) {// 下滑显示
            startShowAnimation(child);
        }
    }

    private void startShowAnimation(@NonNull View child) {
        if (inAnimator == null) {
            inAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0);
            inAnimator.setDuration(200);
        }
        if (!inAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
            inAnimator.start();
        }
        isShow = true;
    }

    private void startHideAnimation(@NonNull View child) {
        if (outAnimator == null) {
            outAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight());
            outAnimator.setDuration(200);
        }
        if (!outAnimator.isRunning() && child.getTranslationY() <= 0) {
            outAnimator.start();
        }
        isShow = false;
    }

    public void setNeedAnimation(boolean needAnimation) {
        isNeedAnimation = needAnimation;
    }
}
