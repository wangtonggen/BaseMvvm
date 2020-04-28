package com.example.basemvvm.network.service;

import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.LoginBean;
import com.example.basemvvm.bean.TokenBean;
import com.example.basemvvm.network.api.UserApi;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author: wtg
 * date:2020/3/14 0014
 * desc:
 */
public interface UserService {
    /**
     * 登录
     *
     * @param params 参数
     * @return 订阅对象
     */
    @FormUrlEncoded
    @POST(UserApi.URL_USER_MEMBER_LOGIN)
    Observable<HttpResponse<LoginBean>> login(@FieldMap Map<String, Object> params);

    /**
     * 发送验证码
     *
     * @param params 参数
     * @return 订阅对象
     */
    @FormUrlEncoded
    @POST(UserApi.URL_USER_MEMBER_SEND_CODE)
    Observable<HttpResponse<String>> sendCode(@FieldMap Map<String, Object> params);

    /**
     * 刷新token
     * @param params 参数
     * @return 订阅对象
     */
    @FormUrlEncoded
    @POST(UserApi.URL_USER_REFRESH_TOKEN)
    Observable<HttpResponse<TokenBean>> refreshToken(@FieldMap Map<String, Object> params);

    /**
     * 刷新token
     * @param params 参数
     * @return 订阅对象
     */
    @FormUrlEncoded
    @POST(UserApi.URL_USER_REFRESH_TOKEN)
    Call<ResponseBody> refreshTokenCall(@FieldMap Map<String, Object> params);
}
