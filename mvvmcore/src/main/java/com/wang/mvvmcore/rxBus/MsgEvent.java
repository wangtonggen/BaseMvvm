package com.wang.mvvmcore.rxBus;

/**
 * author: 王统根
 * time: 2020/7/28
 * desc:
 */
public class MsgEvent {
    private int type;
    private String msg;

    public MsgEvent(int type) {
        this.type = type;
    }

    public MsgEvent(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
