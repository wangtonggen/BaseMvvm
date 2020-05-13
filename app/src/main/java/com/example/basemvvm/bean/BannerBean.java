package com.example.basemvvm.bean;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

/**
 * author: wtg
 * date:2020/5/12 0012
 * desc: banner bean
 */
public class BannerBean extends SimpleBannerInfo {
    private String title;
    private String url;
    private String desc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Object getXBannerUrl() {
        return getUrl();
    }
}
