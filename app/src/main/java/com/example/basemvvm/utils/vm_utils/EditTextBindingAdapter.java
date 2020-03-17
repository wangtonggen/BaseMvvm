package com.example.basemvvm.utils.vm_utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.KeyboardUtils;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc: editView 相关的操作
 */
public class EditTextBindingAdapter {

    /**
     * 设置请求焦点
     * @param editText editText
     * @param needRequestFocus 是否请求焦点 true 请求焦点 false不请求
     */
    @BindingAdapter(value = {"requestFocus"},requireAll = false)
    public static void requestFocusCommand(EditText editText,boolean needRequestFocus){
        if (needRequestFocus) {
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            KeyboardUtils.showSoftInput(editText);
//            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            if (imm != null){
//                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
//            }
        }
        editText.setFocusableInTouchMode(needRequestFocus);
    }
}
