package com.example.basemvvm.utils.common_utils;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.basemvvm.base.MyApplication;

/**
 * 提示类
 */
public class ToastUtils {

    /**
     * 长土司
     * @param content 内容
     */
    public static void showLongToast(String content){
        showLongToast(MyApplication.instance,content);
    }

    /**
     * 短土司
     * @param content 内容
     */
    public static void showShortToast(String content){
        showShortToast(MyApplication.instance,content);
    }

    /**
     * 短土司
     * @param resId 字符串资源id
     */
    public static void showShortToast(@StringRes int resId){
        showShortToast(MyApplication.instance,resId);
    }

    /**
     * 长时间显示吐司
     *
     * @param content 要显示的内容
     */
    public static void showLongToast(Context context, String content) {
        Toast.makeText(context, content,
                Toast.LENGTH_LONG).show();
    }

    /**
     * 短时间显示吐司
     *
     * @param content 显示的内容
     */
    public static void showShortToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示吐司
     *
     * @param resId 资源id
     */
    public static void showShortToast(Context context, @StringRes int resId) {
        ToastUtils.showShortToast(context,StringUtils.getString(context,resId));
    }
}
