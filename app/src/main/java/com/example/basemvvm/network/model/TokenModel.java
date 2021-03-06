package com.example.basemvvm.network.model;

import com.example.basemvvm.bean.TokenBean;
import com.example.basemvvm.network.base.BaseObserver;
import com.example.basemvvm.network.service.UserService;
import com.wang.mvvmcore.base.activity.BaseActivity;
import com.wang.mvvmcore.network.networkBase.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * author: wtg
 * date:2020/4/28 0028
 * desc: 刷新token的业务层
 */
public class TokenModel {
    private static TokenModel tokenModel;
    private static UserService userService = RetrofitManager.getInstance().createService(UserService.class);

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
        return tokenModel;
    }

    /**
     * 刷新token
     *
     * @param oldToken     旧的token
     * @param baseObserver 监听
     */
    public void refreshToken(BaseActivity activity, String oldToken, BaseObserver<TokenBean> baseObserver) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", oldToken);
        userService.refreshToken(params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);
    }

    /**
     * 刷新token
     *
     * @param oldToken 旧的token
     * @return 返回call
     */
    public Call<ResponseBody> refreshTokenCall(String oldToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("token", oldToken);
        return userService.refreshTokenCall(params);
    }
}
