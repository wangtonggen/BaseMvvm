package com.example.basemvvm.utils.commonUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    public static MultipartBody imagesToMultipartBodyString(String key, List<String> files){
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(new File(file));
        }
        return imagesToMultipartBody(key,fileList);
    }
    /**
     * 指定image 类型
     * @param key 文件key
     * @param files 文件列表
     * @return MultipartBody
     */
    public static MultipartBody imagesToMultipartBody(String key, List<File> files) {
        return filesToMultipartBody(key, MediaType.parse("image/png"), files);
    }

    /**
     * 指定文件的类型的
     * @param key 文件的key
     * @param contentType 文件类型
     * @param files 文件列表
     * @return MultipartBody
     */
    public static MultipartBody filesToMultipartBody(String key, @Nullable MediaType contentType, List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(contentType, file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

    public static List<MultipartBody.Part> imagesToMultipartBodyPartsString(String key, List<String> files){
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            fileList.add(new File(file));
        }
        return imagesToMultipartBodyParts(key,fileList);
    }

    /**
     * 指定image 类型
     * @param key 文件key
     * @param files 文件列表
     * @return BodyPart
     */
    public static List<MultipartBody.Part> imagesToMultipartBodyParts(@NonNull String key, @NonNull List<File> files) {
        return filesToMultipartBodyParts(key, MediaType.parse("image/png"), files);
    }


    /**
     * 转换成 BodyParts 类型
     * @param key key
     * @param contentType 文件类型
     * @param files 文件集合
     * @return BodyParts
     */
    public static List<MultipartBody.Part> filesToMultipartBodyParts(@NonNull String key, @Nullable MediaType contentType, @NonNull List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(contentType, file);
            MultipartBody.Part part = MultipartBody.Part.createFormData(key, file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }
}
