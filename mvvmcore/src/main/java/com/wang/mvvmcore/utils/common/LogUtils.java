package com.wang.mvvmcore.utils.common;

import android.util.Log;

import com.wang.mvvmcore.BuildConfig;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class LogUtils {
    private static final String TAG = "LogUtils";

    public static void logE(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    public static void logE(String message) {
        logE(TAG, message);
    }
}
