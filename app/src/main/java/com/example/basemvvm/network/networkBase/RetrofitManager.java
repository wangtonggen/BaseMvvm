package com.example.basemvvm.network.networkBase;

import android.text.TextUtils;

import com.example.basemvvm.base.app.BaseApplication;
import com.example.basemvvm.utils.common.LogUtils;
import com.example.basemvvm.utils.common.ToastUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.basemvvm.network.networkBase.ApiConfigConstant.CONNECT_TIMEOUT;
import static com.example.basemvvm.network.networkBase.ApiConfigConstant.READ_TIMEOUT;
import static com.example.basemvvm.network.networkBase.ApiConfigConstant.WRITE_TIMEOUT;

/**
 * author: wtg
 * date:2020/3/13 0013
 * desc:
 */
public class RetrofitManager {
    private static RetrofitManager instance;
    private static Retrofit retrofit;

    private OkHttpClient okHttpClient;
    private int readTimeout = READ_TIMEOUT;
    private TimeUnit readTimeoutTimeUnit = TimeUnit.SECONDS;
    private int connectTimeout = CONNECT_TIMEOUT;
    private TimeUnit connectTimeoutTimeUnit = TimeUnit.SECONDS;
    private int writeTimeout = WRITE_TIMEOUT;
    private TimeUnit writeTimeoutTimeUnit = TimeUnit.SECONDS;
    private Converter.Factory converterFactory = GsonConverterFactory.create();
    private CallAdapter.Factory callAdapterFactory = RxJava2CallAdapterFactory.create();
    private List<Interceptor> interceptors = new ArrayList<>();
    private String baseUrl;
    private RetrofitManager() {

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
     * 格式转换器
     * @param converterFactory 格式转换器
     * @return RetrofitManager
     */
    public RetrofitManager setConverterFactory(Converter.Factory converterFactory) {
        this.converterFactory = converterFactory;
        return instance;
    }

    /**
     * 适配器
     * @param callAdapterFactory 适配器
     * @return RetrofitManager
     */
    public RetrofitManager setCallAdapterFactory(CallAdapter.Factory callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
        return instance;
    }

    /**
     * 设置读取超时时间
     *
     * @param readTimeout 时间
     * @param timeUnit    单位
     * @return RetrofitManager
     */
    public RetrofitManager setReadTimeout(int readTimeout, TimeUnit timeUnit) {
        this.readTimeout = readTimeout;
        this.readTimeoutTimeUnit = timeUnit;
        return instance;
    }

    /**
     * 设置连接超时时间
     *
     * @param connectTimeout 时间
     * @param timeUnit       单位
     * @return RetrofitManager
     */
    public RetrofitManager setConnectTimeout(int connectTimeout, TimeUnit timeUnit) {
        this.connectTimeout = connectTimeout;
        this.connectTimeoutTimeUnit = timeUnit;
        return instance;
    }

    /**
     * 设置baseUrl
     * @param baseUrl url
     */
    public RetrofitManager setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return instance;
    }

    /**
     * 设置写入超时时间
     *
     * @param writeTimeout 时间
     * @param timeUnit     单位
     * @return RetrofitManager
     */
    public RetrofitManager setWriteTimeout(int writeTimeout, TimeUnit timeUnit) {
        this.writeTimeout = writeTimeout;
        this.writeTimeoutTimeUnit = timeUnit;
        return instance;
    }


    /**
     * 添加拦截器
     *
     * @param interceptor 拦截器
     * @return RetrofitManager
     */
    public RetrofitManager addInterceptor(Interceptor interceptor) {
        if (!interceptors.contains(interceptor)) {
            interceptors.add(interceptor);
        }
        return instance;
    }

    /**
     * 初始化
     */
    public void init() {
        if (TextUtils.isEmpty(baseUrl)){
            ToastUtils.showShortToast("请设置baseUrl");
            return;
        }
        if (okHttpClient == null){
            //添加拦截器
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((@NotNull String message) -> LogUtils.logE("okhttp4:", message));
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(connectTimeout, connectTimeoutTimeUnit);
            builder.readTimeout(readTimeout, readTimeoutTimeUnit)
                    .writeTimeout(writeTimeout, writeTimeoutTimeUnit)
                    .sslSocketFactory(SSL.initSSLSocketFactory(), SSL.initTrustManager())
                    .addInterceptor(httpLoggingInterceptor)
                    //添加下载拦截器
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.instance)));
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
            okHttpClient = builder.build();
        }
        retrofit = new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl(baseUrl)
                .build();
    }

    /**
     * 获取okhttpClient
     *
     * @return okhttpClient
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public RetrofitManager setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
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
