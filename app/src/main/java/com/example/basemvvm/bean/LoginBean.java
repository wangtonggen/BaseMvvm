package com.example.basemvvm.bean;

/**
 * author: wtg  2019/4/24 0024
 * desc: 用户登录的bean
 */
public class LoginBean {
   private String userName;
   private String msg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
