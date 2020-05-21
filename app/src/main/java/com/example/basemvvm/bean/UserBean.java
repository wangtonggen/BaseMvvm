package com.example.basemvvm.bean;

import com.wang.mvvmcore.adapter.entity.BaseMultiEntity;

/**
 * author: wtg
 * date:2020/4/30 0030
 * desc:
 */
public class UserBean extends BaseMultiEntity {
    private int type;
    private String url;

    public UserBean(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
