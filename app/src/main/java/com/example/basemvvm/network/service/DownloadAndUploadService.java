package com.example.basemvvm.network.service;

import androidx.annotation.NonNull;

import com.example.basemvvm.bean.HttpResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static com.example.basemvvm.network.api.UploadApi.URL_UPLOAD_IMAGE;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 下载文件的service
 */
public interface DownloadAndUploadService {
    @Streaming
    @GET
    Observable<ResponseBody> download(@NonNull @Url String url);

    /**
     * 上传文件
     *
     * @param parts  文件列表
     * @param params 参数
     * @return 监听
     */
    @Multipart
    @POST(URL_UPLOAD_IMAGE)
    Observable<HttpResponse<String>> uploadImage(@Part List<MultipartBody.Part> parts, @FieldMap Map<String, Object> params);

    /**
     * 上传文件
     *
     * @param multipartBody 文件
     * @param params        参数
     * @return 监听
     */
    @POST("/commitment/published")
    Observable<HttpResponse<String>> uploadImage(@Body MultipartBody multipartBody, @FieldMap Map<String, Object> params);
}
