package com.wang.mvvmcore.adapter.multi.binder;

import com.chad.library.adapter.base.binder.QuickItemBinder;
import com.wang.mvvmcore.adapter.multi.adapter.BaseMultiBinderAdapter;

/**
 * author: wtg
 * date:2020/5/20 0020
 * desc: 不使用dataBinding 的binder 在使用{@link BaseMultiBinderAdapter} 必须继承该类
 *
 * @param <T> bean
 */
public abstract class BaseBinder<T> extends QuickItemBinder<T> {

}
