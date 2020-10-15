package com.wang.mvvmcore.utils.common;

import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class CoreLogUtils {
    private static final String TAG = "CoreLogUtils";//默认tag
    private static boolean isPrintLog = false;//是否打印日志 默认是不打印

    /**
     * 打印log
     *
     * @param tag     tag
     * @param message 内容
     */
    public static void logE(String tag, String message) {
        if (isPrintLog) {
            if (TextUtils.isEmpty(tag)) {
                LogUtils.e(message);
            } else {
                LogUtils.e(tag, message);
            }
        }
    }

    /**
     * 打印log
     *
     * @param message 消息
     */
    public static void logE(String message) {
        logE(TAG, message);
    }


    /**
     * 打印日志级别
     *
     * @param tag     tag
     * @param message 信息
     */
    public static void logV(String tag, String message) {
        if (isPrintLog) {
            if (TextUtils.isEmpty(tag)) {
                com.blankj.utilcode.util.LogUtils.v(message);
            } else {
                com.blankj.utilcode.util.LogUtils.vTag(tag, message);
            }
        }
    }

    /**
     * 打印日志级别
     *
     * @param message 消息
     */
    public static void logV(String message) {
        logV(TAG, message);
    }

    /**
     * 打印日志级别
     *
     * @param tag     tag
     * @param message 日志
     */
    public static void logD(String tag, String message) {
        if (isPrintLog) {
            if (TextUtils.isEmpty(tag)) {
                com.blankj.utilcode.util.LogUtils.d(message);
            } else {
                com.blankj.utilcode.util.LogUtils.dTag(tag, message);
            }
        }
    }

    /**
     * 打印日志级别
     *
     * @param message 信息
     */
    public static void logD(String message) {
        logD(TAG, message);
    }

    /**
     * 打印日志级别
     *
     * @param tag     tag
     * @param message 信息
     */
    public static void logInfo(String tag, String message) {
        if (isPrintLog) {
            if (TextUtils.isEmpty(tag)) {
                com.blankj.utilcode.util.LogUtils.i(message);
            } else {
                com.blankj.utilcode.util.LogUtils.iTag(tag, message);
            }
        }
    }

    /**
     * 打印日志级别
     *
     * @param message 信息
     */
    public static void logInfo(String message) {
        logInfo(TAG, message);
    }

    /**
     * 打印日志级别
     *
     * @param tag     tag
     * @param message 消息
     */
    public static void logW(String tag, String message) {
        if (isPrintLog) {
            if (TextUtils.isEmpty(tag)) {
                com.blankj.utilcode.util.LogUtils.w(message);
            } else {
                com.blankj.utilcode.util.LogUtils.wTag(tag, message);
            }
        }
    }

    /**
     * 打印日志级别
     *
     * @param message 消息
     */
    public static void logW(String message) {
        logW(TAG, message);
    }

    /**
     * 设置是否打印日志
     *
     * @param isPrintLog false(默认)不打印 true 打印
     */
    public static void setIsPrintLog(boolean isPrintLog) {
        CoreLogUtils.isPrintLog = isPrintLog;
    }
}
