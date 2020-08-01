package com.wang.mvvmcore.utils.common;

import android.content.Context;

import androidx.annotation.StringRes;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;

/**
 * author: wtg  2019/4/24 0024
 * desc: 字符串处理类
 */
public class MyStringUtils {
    /**
     * 加密电话中间四位显示*
     *
     * @param mobile 手机号
     * @return 字符串
     */
    public static String getEncodeMobile(String mobile) {
        return mobile.substring(0, mobile.length() - mobile.substring(3).length()) + "****" + mobile.substring(7);
    }

    /**
     * 格式化字符串
     *
     * @param context 上下文
     * @param resId   字符资源id
     * @param args    参数
     * @return 字符串
     */
    public static String strFormat(Context context, @StringRes int resId, Object... args) {
        return String.format(StringUtils.getString(resId), args);
    }

    /**
     * 格式化字符串
     *
     * @param format 需要格式化的字符串
     * @param args   参数
     * @return 字符串
     */
    public static String strFormat(String format, Object... args) {
        return String.format(format, args);
    }

}
