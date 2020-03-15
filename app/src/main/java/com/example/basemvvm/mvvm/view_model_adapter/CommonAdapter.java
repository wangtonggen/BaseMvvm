package com.example.basemvvm.mvvm.view_model_adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.example.basemvvm.utils.common_utils.LogUtils;

/**
 * author：wtg
 * time：2020/3/15
 * desc：
 */
public class CommonAdapter {
    @BindingAdapter("set_focus")
    public static void hasFocus(View view,boolean focus){
        LogUtils.logE("333="+focus);
        if (focus){
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }else {
            ((View)view.getParent()).setFocusable(true);
            ((View)view.getParent()).setFocusableInTouchMode(true);
        }
    }
}
