package com.wang.mvvmcore.bindAdapter.command;

/**
 * author: wtg
 * date:2020/3/16 0016
 * desc:
 */
public interface BindingConsumer<T> {
    void call(T t);
}
