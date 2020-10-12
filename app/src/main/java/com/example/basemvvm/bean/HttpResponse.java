package com.example.basemvvm.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * author: wtg  2019/4/24 0024
 * desc: 基础的bean类采用泛型 可以定制的数据标准
 *       只需重新定义基本bean 然后放到相应的{@link com.example.basemvvm.network.service.UserService}里面就可以了
 */
public class HttpResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
