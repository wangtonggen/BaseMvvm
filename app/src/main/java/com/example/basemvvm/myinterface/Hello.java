package com.example.basemvvm.myinterface;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * author: wtg
 * date:2020/3/18 0018
 * desc: 注解代替枚举
 */
@Retention(RetentionPolicy.SOURCE)
public @interface Hello {
    int UN_KNOW = -1;
    int UN_STAT = 0;
}
