package com.wang.mvvmcore.utils.common;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author: wtg
 * date:2020/5/6 0006
 * desc: 倒计时工具类
 */
public class CountDownUtils {
    /**
     * 倒计时
     *
     * @param stepTime  Long 间隔的时间
     * @param totalTime Long 总共的时间
     * @param observer  Observer<Long>
     */
    public static void countDown(Long stepTime, Long totalTime, DefaultObserver<Long> observer) {
        Observable.interval(0, stepTime, TimeUnit.SECONDS)
                .take(totalTime + 1)
                .map((Long aLong) -> totalTime - aLong)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
