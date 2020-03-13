package com.example.basemvvm.utils.vm_utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.databinding.BindingAdapter;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: 提取操作的公共部分
 */
public class CommonViewUtils {

    /**
     * 设置填充左边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableLeft")
    public static void compoundDrawablesLeft(TextView view, @DrawableRes int drawableResId) {
//        view.setCompoundDrawables(view.getResources().getDrawable(R.mipmap.ic_launcher),null,null,null);
        view.setCompoundDrawables(view.getResources().getDrawable(drawableResId), null, null, null);
    }

    /**
     * 设置填充右边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableRight")
    public static void compoundDrawablesRight(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawables(null, null, view.getResources().getDrawable(drawableResId), null);
    }

    /**
     * 设置填充上边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableTop")
    public static void compoundDrawablesTop(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawables(null, view.getResources().getDrawable(drawableResId), null, null);
    }

    /**
     * 设置填充下边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableBottom")
    public static void compoundDrawablesBottom(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawables(null, null, null, view.getResources().getDrawable(drawableResId));
    }
}
