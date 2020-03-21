package com.example.basemvvm.network.network_base;

import com.example.basemvvm.base.BaseApplication;
import com.example.basemvvm.network.api.ApiBaseUrl;
import com.example.basemvvm.network.gson.ResponseConverterFactory;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.example.basemvvm.network.network_base.ApiConfigConstant.CONNECT_TIMEOUT;
import static com.example.basemvvm.network.network_base.ApiConfigConstant.READ_TIMEOUT;
import static com.example.basemvvm.network.network_base.ApiConfigConstant.WRITE_TIMEOUT;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class RetrofitManager {
    private static RetrofitManager instance;
    private static Retrofit retrofit;

    private RetrofitManager() {
        //添加拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((@NotNull String message) -> LogUtils.logE("okhttp4:", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSL.initSSLSocketFactory(), SSL.initTrustManager())
                .addNetworkInterceptor(httpLoggingInterceptor)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.instance)));

        retrofit = new Retrofit.Builder().client(builder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiBaseUrl.URL_BASE)
                .build();
    }

    /**
     * 单例初始化数据
     *
     * @return instance
     */
    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    /**
     * 创建service
     *
     * @param service service
     * @param <T>     类型
     * @return 服务类
     */
    public <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }
}
