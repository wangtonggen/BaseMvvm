package com.example.basemvvm.network.model;

import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.network.network_base.BaseObserver;
import com.example.basemvvm.network.network_base.RetrofitManager;
import com.example.basemvvm.network.service.UserService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
     * @param mobile 手机号
     * @param captcha 验证码
     * @param baseObserver 监听
     */
    public void login(String mobile, String captcha, BaseObserver<LoginBean> baseObserver) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("captcha", captcha);
        userService.login(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 发送验证码
     * @param mobile 手机号
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
