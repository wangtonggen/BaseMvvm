package com.wang.mvvmcore.Interface;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.wang.mvvmcore.constant.SwipeConstant.SWIPE_ALL;
import static com.wang.mvvmcore.constant.SwipeConstant.SWIPE_BOTTOM;
import static com.wang.mvvmcore.constant.SwipeConstant.SWIPE_LEFT;
import static com.wang.mvvmcore.constant.SwipeConstant.SWIPE_NONE;
import static com.wang.mvvmcore.constant.SwipeConstant.SWIPE_RIGHT;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc:
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {SWIPE_LEFT, SWIPE_RIGHT, SWIPE_BOTTOM, SWIPE_ALL, SWIPE_NONE})
public @interface SwipeInterface {

}
