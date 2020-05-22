package com.wang.mvvmcore.bindAdapter;

import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.KeyboardUtils;
import com.wang.mvvmcore.widget.SimpleTextWatcher;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: editView 相关的操作
 */
public class EditTextBindingAdapter {

    /**
     * 设置请求焦点
     *
     * @param editText         editText
     * @param needRequestFocus 是否请求焦点 true 请求焦点 false不请求
     */
    @BindingAdapter(value = {"requestFocus"}, requireAll = false)
    public static void requestFocusCommand(EditText editText, boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            KeyboardUtils.showSoftInput(editText);
        }
        editText.setFocusableInTouchMode(needRequestFocus);
    }

    /**
     * 设置editText 控件的监控监听
     *
     * @param editText          editText
     * @param simpleTextWatcher 监听
     */
    @BindingAdapter(value = {"textWatcher"})
    public static void onEditTextCommand(EditText editText, SimpleTextWatcher simpleTextWatcher) {
        editText.addTextChangedListener(simpleTextWatcher);
    }
}
