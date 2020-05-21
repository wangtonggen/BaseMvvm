package com.wang.mvvmcore.constant;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SDCardUtils;

import java.io.File;

/**
 * author: wtg
 * date:2020/4/1 0001
 * desc: 文件的常量
 */
public class FileConstant {
    //文件处理的根目录
    public static final String DIR_ROOT_BASE = SDCardUtils.getSDCardPathByEnvironment() + File.separator;
    public static final String DIR_ROOT_NAME = AppUtils.getAppName();
    //apk的下载路径
    public static final String DIR_APP = DIR_ROOT_BASE + File.separator + DIR_ROOT_NAME + File.separator + "app";
    //闪退文件的保存路径
    public static String DIR_CRASH = DIR_ROOT_BASE + File.separator + DIR_ROOT_NAME + File.separator + "crash";
}
