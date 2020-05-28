package com.wang.mvvmcore.adapter.entity;

import com.wang.mvvmcore.adapter.provider.BaseBindingProvider;
import com.wang.mvvmcore.adapter.provider.BaseProvider;

import java.io.Serializable;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc: 在使用{@link BaseBindingProvider} 和{@link BaseProvider} 获取的数据bean必须要继承该类 方便更好的获取到item的类型
 */
public abstract class BaseMultiEntity implements Serializable {

    /**
     * 获取item类型
     *
     * @return 类型
     */
    public abstract int getItemType();
}
