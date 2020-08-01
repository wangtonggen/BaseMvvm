package com.wang.mvvmcore.utils.common;

import com.wang.mvvmcore.BuildConfig;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class LogUtils {
    private static final String TAG = "LogUtils";

    /**
     * 打印log
     * @param tag tag
     * @param message 内容
     */
    public static void logE(String tag, String message) {
        if (BuildConfig.DEBUG) {
            com.blankj.utilcode.util.LogUtils.e(tag,message);
        }
    }

    /**
     * 打印log
     * @param message 消息
     */
    public static void logE(String message) {
        logE(TAG, message);
    }
}
