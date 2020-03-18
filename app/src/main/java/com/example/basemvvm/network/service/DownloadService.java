package com.example.basemvvm.network.service;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 下载文件的service
 */
public interface DownloadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@NonNull @Url String url);
}
