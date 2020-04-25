package com.example.basemvvm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * author: wtg
 * date:2020/3/23 0023
 * desc: 多布局bean
 */
public class MultiItemBean implements MultiItemEntity {
    public int type;
    public String name;
    public String desc;

    public MultiItemBean(int type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
