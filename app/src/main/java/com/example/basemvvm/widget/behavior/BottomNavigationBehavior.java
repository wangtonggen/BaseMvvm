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
    private View child;
    private int oldScrollY = 0;

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
        if (this.child == null) {
            this.child = child;
        }
//        LogUtils.logE("child", consumed[1] + "---" + dy + "---" + target.getScrollY());
        if (target.getScrollY() - oldScrollY > 20) {// 上滑隐藏
            if (outAnimator == null) {
                outAnimator = ObjectAnimator.ofFloat(child, "translationY", 0, child.getHeight());
                outAnimator.setDuration(200);
            }
            if (!outAnimator.isRunning() && child.getTranslationY() <= 0) {
                outAnimator.start();
            }
            oldScrollY = target.getScrollY();
        } else if (target.getScrollY() - oldScrollY < -20) {// 下滑显示
            if (inAnimator == null) {
                inAnimator = ObjectAnimator.ofFloat(child, "translationY", child.getHeight(), 0);
                inAnimator.setDuration(200);
            }
            if (!inAnimator.isRunning() && child.getTranslationY() >= child.getHeight()) {
                inAnimator.start();
            }
            oldScrollY = target.getScrollY();
        }
    }
}
