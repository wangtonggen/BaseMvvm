package com.example.basemvvm.network.api;

/**
 * author: wtg
 * date:2020/3/14 0014
 * desc: api
 */
public class UserApi {
    //用户member基准
    private static final String URL_USER_MEMBER_BASE = "member/";
    //登录
    public static final String URL_USER_MEMBER_LOGIN = URL_USER_MEMBER_BASE + "login";

    //发送验证码
    public static final String URL_USER_MEMBER_SEND_CODE = URL_USER_MEMBER_BASE + "message/captcha";
}
