package com.example.basemvvm.utils.common_utils;

import android.content.Context;

import androidx.annotation.StringRes;

/**
 * author: wtg  2019/4/24 0024
 * desc: 字符串处理类
 */
public class StringUtils {
    /**
     * 只能传入String的id
     *
     * @param strId 资源id
     * @return 字符串
     */
    public static String getString(Context context, @StringRes int strId) {
        return context.getResources().getString(strId);
    }

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
     * @param context 上下文
     * @param resId 字符资源id
     * @param args 参数
     * @return 字符串
     */
    public static String strFormat(Context context, @StringRes int resId, Object... args){
        return String.format(getString(context,resId),args);
    }

    /**
     * 格式化字符串
     * @param format 需要格式化的字符串
     * @param args 参数
     * @return 字符串
     */
    public static String strFormat(String format, Object... args){
        return String.format(format,args);
    }

    /**
     * 判断是否是gif图片
     * @param path 图片路径
     * @return true 是 false 否
     */
    public static boolean isGif(String path){
        return (path.endsWith(".gif")||path.endsWith(".GIF"));
    }

    /**
     * dp 转 px
     * @param ctx 上下文
     * @param dp dp
     * @return px
     */
    public static int dp2px(Context ctx, float dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);// 4.9->5 4.4->4
    }
}
