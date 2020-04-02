package com.example.basemvvm.bean;

import java.io.Serializable;

/**
 * author: wtg
 * date:2020/3/19 0019
 * desc: 通知是否需要更新
 */
public class UpdateBean implements Serializable {
    private String newVersionName;
    private int newVersionCode;
    private String downloadUrl;
    private String desc;
    private int updateType;

    public String getNewVersionName() {
        return newVersionName;
    }

    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }

    public int getNewVersionCode() {
        return newVersionCode;
    }

    public void setNewVersionCode(int newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }
}
