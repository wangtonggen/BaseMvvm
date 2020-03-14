package com.example.basemvvm.bean;

import java.io.Serializable;

/**
 * author: wtg  2019/4/24 0024
 * desc: 基础的bean类采用泛型
 */
public class HttpResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
