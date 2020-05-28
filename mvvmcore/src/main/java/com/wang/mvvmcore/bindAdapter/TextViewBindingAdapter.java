package com.wang.mvvmcore.bindAdapter;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: textView 相关adapter
 */
public class TextViewBindingAdapter {

    /**
     * textView 滑动设置
     *
     * @param textView                view
     * @param scrollingMovementMethod 滑动方法
     */
    @BindingAdapter("setMovementMethod")
    public static void setMovementMethod(TextView textView, ScrollingMovementMethod scrollingMovementMethod) {
        textView.setMovementMethod(scrollingMovementMethod);
    }
}
