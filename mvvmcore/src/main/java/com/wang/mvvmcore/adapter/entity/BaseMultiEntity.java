package com.wang.mvvmcore.adapter.entity;

import java.io.Serializable;

/**
 * author: wtg
 * date:2020/4/25 0025
 * desc:
 */
public abstract class BaseMultiEntity implements Serializable {

    /**
     * 获取item类型
     *
     * @return 类型
     */
    public abstract int getItemType();
}
