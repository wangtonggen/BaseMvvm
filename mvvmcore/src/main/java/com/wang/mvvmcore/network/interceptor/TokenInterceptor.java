package com.wang.mvvmcore.network.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: wtg
 * date:2020/4/28 0028
 * desc: 单纯添加token的拦截器
 */
public class TokenInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder().header("Authorization", "Bearer " + "token");
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
