package com.example.basemvvm.network;

import com.example.basemvvm.base.MyApplication;
import com.example.basemvvm.network.exception.RetrofitException;
import com.example.basemvvm.utils.common_utils.LogUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.basemvvm.network.ApiConfigConstant.CONNECT_TIMEOUT;
import static com.example.basemvvm.network.ApiConfigConstant.READ_TIMEOUT;
import static com.example.basemvvm.network.ApiConfigConstant.WRITE_TIMEOUT;
import static com.example.basemvvm.network.ApiConstant.URL_BASE;

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
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String message) {
                LogUtils.logE("okhttp4:",message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSL.initSSLSocketFactory(),SSL.initTrustManager())
                .addNetworkInterceptor(httpLoggingInterceptor)
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(MyApplication.instance)));

        retrofit = new Retrofit.Builder().client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL_BASE)
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
     * @param service service
     * @param <T> 类型
     * @return 服务类
     * @throws RetrofitException 异常
     */
    public <T> T createService(Class<T> service) throws RetrofitException {
        if (retrofit == null){
            throw new RetrofitException("retrofit is null");
        }
        return retrofit.create(service);
    }
}
