package com.wang.mvvmcore.adapter.multi.provider;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.wang.mvvmcore.adapter.entity.BaseMultiEntity;

/**
 * author: wtg
 * date:2020/5/27 0027
 * desc: provider 不使用dataBinding
 * @param <T> bean
 */
public abstract class BaseProvider<T extends BaseMultiEntity> extends BaseItemProvider<T> {

}
