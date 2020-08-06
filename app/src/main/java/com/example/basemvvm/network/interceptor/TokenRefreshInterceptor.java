package com.example.basemvvm.network.interceptor;

import com.blankj.utilcode.util.GsonUtils;
import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.bean.TokenBean;
import com.example.basemvvm.constant.CodeConstant;
import com.example.basemvvm.network.model.TokenModel;
import com.google.gson.reflect.TypeToken;
import com.wang.mvvmcore.utils.common.OkhttpResponseUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * author: wtg
 * date:2020/4/28 0028
 * desc: 添加token并自动刷新token的拦截器
 */
public class TokenRefreshInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder().header("Authorization", "Bearer " + "token");
        Request request = requestBuilder.build();
        Response response = chain.proceed(request);
        if (isTokenExpired(response)) {//过期
            //同步请求方式，获取最新的Token
            String newToken = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Authorization", "Basic " + newToken)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response 响应数据
     * @return true 过期 false 未过期
     */
    private boolean isTokenExpired(Response response) {
        return response.code() == CodeConstant.CODE_TOKEN_EXPIRED;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return token
     */
    private String getNewToken() throws IOException {
        // 通过获取token的接口，同步请求接口
        Call<ResponseBody> responseBodyCall = TokenModel.getInstance().refreshTokenCall("");
        String result = OkhttpResponseUtils.getResponseBody(Objects.requireNonNull(responseBodyCall.execute().body()));
        Type type = new TypeToken<HttpResponse<TokenBean>>() {
        }.getType();
        HttpResponse<TokenBean> tokenBeanHttpResponse = GsonUtils.fromJson(result, type);
        return tokenBeanHttpResponse.getData().getToken();
    }
}
