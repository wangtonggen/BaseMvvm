package com.wang.mvvmcore.bindAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

/**
 * author：wtg
 * time：2020/3/15
 * desc：提取操作的公共部分
 */
public class CommonBindingAdapter {

    /**
     * 设置设置背景颜色值/背景图
     *
     * @param view    view
     * @param bgResId 资源id
     */
    @BindingAdapter(value = {"view_background", "view_Alpha"}, requireAll = false)
    public static void setBgRes(View view, int bgResId, @FloatRange(from = 0.0, to = 1.0) float alpha) {
        if (view == null) {
            return;
        }
        view.setBackgroundResource(bgResId);
//        view.setAlpha(alpha);
        view.getBackground().mutate().setAlpha((int) (alpha * 255));
    }

    /**
     * 设置填充左边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableLeft")
    public static void compoundDrawablesLeft(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawablesWithIntrinsicBounds(view.getResources().getDrawable(drawableResId), null, null, null);
    }

    /**
     * 设置填充右边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableRight")
    public static void compoundDrawablesRight(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, view.getResources().getDrawable(drawableResId), null);
    }

    /**
     * 设置填充上边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableTop")
    public static void compoundDrawablesTop(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawablesWithIntrinsicBounds(null, view.getResources().getDrawable(drawableResId), null, null);
    }

    /**
     * 设置填充下边布局
     *
     * @param view          TextView 和其子类
     * @param drawableResId drawable
     */
    @BindingAdapter("compoundDrawableBottom")
    public static void compoundDrawablesBottom(TextView view, @DrawableRes int drawableResId) {
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, null, view.getResources().getDrawable(drawableResId));
    }

    /**
     * 设置字体颜色
     *
     * @param textView   view
     * @param resColorId 资源id
     */
    @BindingAdapter(value = {"text_color"}, requireAll = false)
    public static void setTextColor(TextView textView, @ColorRes int resColorId) {
        textView.setTextColor(textView.getResources().getColor(resColorId));
    }

    @BindingAdapter(value = {"text_alpha"}, requireAll = false)
    public static void setTextAlpha(TextView textView, @FloatRange(from = 0.0f, to = 1.0f) float alpha) {
        textView.setAlpha(alpha);
    }

    /**
     * 设置hint字体颜色
     *
     * @param textView   所有继承AppCompatTextView 的类
     * @param resColorId 颜色id
     */
    @BindingAdapter("text_hint_color")
    public static void setTextHintColor(AppCompatTextView textView, @ColorRes int resColorId) {
        textView.setHintTextColor(textView.getResources().getColor(resColorId));
    }
}
