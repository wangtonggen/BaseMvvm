package com.wang.mvvmcore.utils.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wang.mvvmcore.network.downloadAndUpload.upload.UploadRequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: wtg
 * date:2020/4/2 0002
 * desc: 对文件上传 的处理类
 */
public class UploadBodyUtils {

    /**
     * 单文件
     *
     * @param filePath 文件路径
     * @param key      key
     * @return MultipartBody
     */
    public static MultipartBody image2MultipartBodyPath(String filePath, String key) {
        return image2MultipartBody(new File(filePath), key);
    }

    /**
     * 单文件
     *
     * @param filePath 文件路径
     * @param key      key
     * @return MultipartBody
     */
    public static MultipartBody image2MultipartBodyPath(String filePath, String key, UploadRequestBody.LoadingListener loadingListener) {
        return image2MultipartBodyProgress(new File(filePath), key, loadingListener);
    }

    /**
     * 单文件
     *
     * @param file 文件
     * @param key  key
     * @return MultipartBody
     */
    public static MultipartBody image2MultipartBody(File file, String key) {
        return singleMultipartBody(file, key, MediaType.parse("image/png"), null);
    }

    /**
     * 单文件
     *
     * @param file            文件
     * @param key             key
     * @param loadingListener 上传监听
     * @return MultipartBody
     */
    public static MultipartBody image2MultipartBodyProgress(File file, String key, UploadRequestBody.LoadingListener loadingListener) {
        return singleMultipartBody(file, key, MediaType.parse("image/png"), loadingListener);
    }

    /**
     * 单文件上传
     *
     * @param file            文件
     * @param key             key
     * @param contentType     文件类型
     * @param loadingListener 监听
     * @return MultipartBody
     */
    public static MultipartBody singleMultipartBody(File file, String key, @Nullable MediaType contentType, UploadRequestBody.LoadingListener loadingListener) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(contentType, file);
        if (loadingListener != null) {
            UploadRequestBody uploadRequestBody = new UploadRequestBody(requestBody, loadingListener);
            builder.addFormDataPart(key, file.getName(), uploadRequestBody);
        } else {
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * 多文件
     * @param key key
     * @param files 文件列表
     * @return MultipartBody
     */
    public static MultipartBody images2MultipartBodyPath(String key, List<String> files) {
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(new File(file));
        }
        return images2MultipartBody(key, fileList);
    }

    /**
     * 指定image 类型
     *
     * @param key   文件key
     * @param files 文件列表
     * @return MultipartBody
     */
    public static MultipartBody images2MultipartBody(String key, List<File> files) {
        return files2MultipartBody(key, MediaType.parse("image/png"), files);
    }

    /**
     * 指定文件的类型的
     *
     * @param key         文件的key
     * @param contentType 文件类型
     * @param files       文件列表
     * @return MultipartBody
     */
    public static MultipartBody files2MultipartBody(String key, @Nullable MediaType contentType, List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(contentType, file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    /**
     * 多文件
     * @param key key
     * @param filePaths 文件路径列表
     * @return List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> images2MultipartBodyPartsPath(String key, List<String> filePaths) {
        List<File> fileList = new ArrayList<>();
        for (String file : filePaths) {
            fileList.add(new File(file));
        }
        return images2MultipartBodyParts(key, fileList);
    }

    /**
     * 指定image 类型
     *
     * @param key   文件key
     * @param files 文件列表
     * @return List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> images2MultipartBodyParts(@NonNull String key, @NonNull List<File> files) {
        return files2MultipartBodyParts(key, MediaType.parse("image/png"), files);
    }


    /**
     * 转换成 BodyParts 类型
     *
     * @param key         key
     * @param contentType 文件类型
     * @param files       文件集合
     * @return List<MultipartBody.Part>
     */
    public static List<MultipartBody.Part> files2MultipartBodyParts(@NonNull String key, @Nullable MediaType contentType, @NonNull List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(contentType, file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
