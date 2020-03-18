package com.example.basemvvm.network.gson;

import com.example.basemvvm.bean.HttpResponse;
import com.example.basemvvm.network.exception.ResultException;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 自定义Gson响应体变换器
 */
public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Nullable
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        HttpResponse httpResult = gson.fromJson(response, HttpResponse.class);
        if (httpResult.getCode() == 200) {
            //200的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            return gson.fromJson(response, type);
        } else {
            //抛一个自定义ResultException 传入失败时候的状态码，和信息
            try {
                throw new ResultException(httpResult.getCode(), httpResult.getMsg());
            } catch (ResultException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
