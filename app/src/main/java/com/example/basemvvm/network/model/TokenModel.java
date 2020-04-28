package com.example.basemvvm.network.model;

import com.example.basemvvm.bean.TokenBean;
import com.example.basemvvm.network.networkBase.BaseObserver;
import com.example.basemvvm.network.networkBase.RetrofitManager;
import com.example.basemvvm.network.service.UserService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * author: wtg
 * date:2020/4/28 0028
 * desc: 刷新token的业务层
 */
public class TokenModel {
    private static TokenModel tokenModel;
    private static UserService userService;

    private TokenModel() {
    }

    public static TokenModel getInstance() {
        if (tokenModel == null) {
            synchronized (UserModel.class) {
                if (tokenModel == null) {
                    tokenModel = new TokenModel();
                }
            }
        }
        if (userService == null) {
            userService = RetrofitManager.getInstance().createService(UserService.class);
        }
        return tokenModel;
    }

    /**
     * 刷新token
     * @param oldToken 旧的token
     * @param baseObserver 监听
     */
    public void refreshToken(String oldToken, BaseObserver<TokenBean> baseObserver) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", oldToken);
        userService.refreshToken(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 刷新token
     * @param oldToken 旧的token
     * @return 返回call
     */
    public Call<ResponseBody> refreshTokenCall(String oldToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", oldToken);
       return userService.refreshTokenCall(params);
    }
}
