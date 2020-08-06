package com.example.basemvvm.network.model;

import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.network.base.BaseObserver;
import com.example.basemvvm.network.service.UserService;
import com.wang.mvvmcore.network.networkBase.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * author: wtg
 * date:2020/3/14 0014
 * desc:
 */
public class UserModel {
    private static UserModel userModel;
    private static UserService userService;

    public static UserModel getInstance() {
        if (userModel == null) {
            synchronized (UserModel.class) {
                if (userModel == null) {
                    userModel = new UserModel();
                }
            }
        }
        if (userService == null) {
            userService = RetrofitManager.getInstance().createService(UserService.class);
        }
        return userModel;
    }

    /**
     * 登录
     *
     * @param mobile       手机号
     * @param captcha      验证码
     * @param baseObserver 监听
     */
    public void login(String mobile, String captcha, BaseObserver<LoginBean> baseObserver) {
        Map<String, Object> params = new HashMap<>();
        params.put("account", mobile);
        params.put("password", captcha);
        userService.login(params)
                .subscribeOn(Schedulers.io())
                .subscribe(baseObserver);
    }

    /**
     * 发送验证码
     *
     * @param mobile       手机号
     * @param baseObserver 监听
     */
    public void sendCode(String mobile, BaseObserver<String> baseObserver) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        userService.sendCode(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }
}
