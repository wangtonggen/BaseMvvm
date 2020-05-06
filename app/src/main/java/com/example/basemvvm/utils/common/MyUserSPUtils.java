package com.example.basemvvm.utils.common;

import com.blankj.utilcode.util.SPUtils;
import com.example.basemvvm.constant.SPConstant;

/**
 * author: wtg
 * date:2020/5/5 0005
 * desc: 封装SPutils
 */
public class MyUserSPUtils {

    /**
     * 存入是否已登录
     * @param isLogin true 已登录 false 未登录
     */
    public static void setIsLogin(boolean isLogin){
        SPUtils.getInstance().put(SPConstant.IS_LOGIN,isLogin);
    }

    /**
     * 获取是否登录
     * @return true 已登录 false 未登录
     */
    public static boolean isLogin(){
        return SPUtils.getInstance().getBoolean(SPConstant.IS_LOGIN);
    }

    /**
     * 存入头像url
     * @param userHeadUrl url
     */
    public static void setHeadUrl(String userHeadUrl) {
        SPUtils.getInstance().put(SPConstant.USER_HEAD_URL, userHeadUrl);
    }

    /**
     * 获取头像
     * @return url
     */
    public static String getHeadUrl() {
        return SPUtils.getInstance().getString(SPConstant.USER_HEAD_URL, null);
    }

    /**
     * 存入用户名
     * @param userName 用户名
     */
    public static void setUserName(String userName){
        SPUtils.getInstance().put(SPConstant.USER_NAME,userName);
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public static String getUserName(){
        return SPUtils.getInstance().getString(SPConstant.USER_NAME,null);
    }

    /**
     * 存入手机号
     * @param mobile 手机号
     */
    public static void setUserMobile(String mobile){
        SPUtils.getInstance().put(SPConstant.USER_MOBILE,mobile);
    }

    /**
     * 获取手机号
     * @return 手机号
     */
    public static String getUserMobile(){
        return SPUtils.getInstance().getString(SPConstant.USER_MOBILE,null);
    }

    /**
     * 退出登录时清空登录数据
     */
    public static void loginOutClear(){
        setIsLogin(false);
        setHeadUrl(null);
        setUserName(null);
        setUserMobile(null);
    }
}
