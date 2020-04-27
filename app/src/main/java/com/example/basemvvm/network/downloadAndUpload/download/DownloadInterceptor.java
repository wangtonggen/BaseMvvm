package com.example.basemvvm.network.downloadAndUpload.download;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * author: wtg  2019/9/2 0002
 * desc: 下载的拦截器
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadResponseBody.DownloadListener downloadListener;
    private long startPoint = -1;

    public DownloadInterceptor(DownloadResponseBody.DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    public DownloadInterceptor(DownloadResponseBody.DownloadListener downloadListener, long startPoint) {
        this.downloadListener = downloadListener;
        this.startPoint = startPoint;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (startPoint == -1) {
            return response.newBuilder().body(
                    new DownloadResponseBody(response.body(), downloadListener)).build();
        } else {
            return response.newBuilder().body(
                    new DownloadResponseBody(response.body(), downloadListener, startPoint)).build();
        }

    }
}
