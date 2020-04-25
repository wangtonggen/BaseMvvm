package com.example.basemvvm.network.downloadAndUpload.download;

import com.example.basemvvm.base.app.BaseApplication;
import com.example.basemvvm.network.networkBase.SSL;
import com.example.basemvvm.utils.commonUtils.LogUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.basemvvm.network.networkBase.ApiConfigConstant.READ_TIMEOUT;
import static com.example.basemvvm.network.networkBase.ApiConfigConstant.WRITE_TIMEOUT;

/**
 * author: wtg
 * date:2020/4/2 0002
 * desc: 需要监听下载进度的工具类
 */
public class DownLoadUtils {

    /**
     * 下载文件 无断点续传
     *
     * @param url              地址
     * @param downloadListener 下载监听
     * @param callback         下载完成回调
     */
    public static Call download(String url, DownloadResponseBody.DownloadListener downloadListener, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((@NotNull String message) -> LogUtils.logE("okhttp4_download:", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSL.initSSLSocketFactory(), SSL.initTrustManager())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new DownloadInterceptor(downloadListener))
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.instance)));
        // 发起请求
        Call call = builder.build().newCall(request);
        call.enqueue(callback);
        return call;
    }

    /**
     * 下载文件 断点续传
     *
     * @param url              地址
     * @param downloadListener 下载监听
     * @param startPoint       开始下载的位置
     * @param callback         下载完成回调
     */
    public static Call downloadBreakpointResume(String url, DownloadResponseBody.DownloadListener downloadListener, long startPoint, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .header("RANGE", "bytes=" + startPoint + "-")//断点续传
                .build();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor((@NotNull String message) -> LogUtils.logE("okhttp4_download:", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .sslSocketFactory(SSL.initSSLSocketFactory(), SSL.initTrustManager())
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new DownloadInterceptor(downloadListener, startPoint))
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.instance)));
        // 发起请求
        Call call = builder.build().newCall(request);
        call.enqueue(callback);
        return call;
    }
}
