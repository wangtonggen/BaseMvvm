package com.example.basemvvm.network.exception;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 返回失败处理
 */
public class ResultException extends Exception {
    private int code;
    public ResultException(int code,String message) {
        super(message);
    }

    public int getCode() {
        return code;
    }
}
