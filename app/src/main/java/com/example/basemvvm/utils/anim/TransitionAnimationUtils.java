package com.example.basemvvm.utils.anim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import static com.example.basemvvm.constant.ParameterConstant.BUNDLE;

/**
 * author: wtg
 * date:2020/5/6 0006
 * desc: 转场动画的工具类
 */
public class TransitionAnimationUtils {

    /**
     * 共享元素动画
     *
     * @param activity          当前activity
     * @param targetActivity    目标activity
     * @param transitionView    执行共享动画的View
     * @param sharedElementName 关联动画的View的名称
     */
    public static void startSceneTransitionAnimationActivity(AppCompatActivity activity, Class targetActivity, View transitionView, String sharedElementName) {
        Intent intent = new Intent(activity, targetActivity);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, sharedElementName);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    /**
     * 共享元素动画
     *
     * @param activity          当前activity
     * @param targetActivity    目标activity
     * @param bundle            bundle参数
     * @param transitionView    执行共享动画的View
     * @param sharedElementName 关联动画的View的名称
     */
    public static void startSceneTransitionAnimationActivity(AppCompatActivity activity, Class targetActivity, Bundle bundle, View transitionView, String sharedElementName) {
        Intent intent = new Intent(activity, targetActivity);
        intent.putExtra(BUNDLE, bundle);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionView, sharedElementName);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    /**
     * 共享元素动画
     *
     * @param activity       当前activity
     * @param targetActivity 目标activity
     * @param sharedElements 执行共享动画的View和名称
     */
    @SafeVarargs
    public static void startSceneTransitionAnimationActivity(AppCompatActivity activity, Class targetActivity, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(activity, targetActivity);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    /**
     * 共享元素动画
     *
     * @param activity       当前activity
     * @param targetActivity 目标activity
     * @param sharedElements 执行共享动画的View和名称
     */
    @SafeVarargs
    public static void startSceneTransitionAnimationActivity(AppCompatActivity activity, Class targetActivity, Bundle bundle, Pair<View, String>... sharedElements) {
        Intent intent = new Intent(activity, targetActivity);
        intent.putExtra(BUNDLE, bundle);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedElements);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
