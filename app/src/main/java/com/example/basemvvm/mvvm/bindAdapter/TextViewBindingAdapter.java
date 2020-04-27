package com.example.basemvvm.mvvm.bindAdapter;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: textView 相关adapter
 */
public class TextViewBindingAdapter {

    @BindingAdapter("setMovementMethod")
    public static void setMovementMethod(TextView textView, ScrollingMovementMethod scrollingMovementMethod) {
        textView.setMovementMethod(scrollingMovementMethod);
    }
}
