package com.wang.mvvmcore.utils.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * author: wtg
 * date:2020/4/28 0028
 * desc: 响应数据的处理
 */
public class OkhttpResponseUtils {
    public static String getResponseBody(ResponseBody responseBody) throws IOException {
        BufferedSource source = responseBody.source();
        // 获取全部body的数据
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        // 在读取缓存去之前clone数据，解决response.body().string()只能读取一次的问题
        return buffer.clone().readString(StandardCharsets.UTF_8);
    }
}
