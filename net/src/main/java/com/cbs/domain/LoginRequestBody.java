package com.cbs.domain;

/**
 * Created by yangshaowei on 2017/5/8.
 */

public class LoginRequestBody {

    String account;  //用户名
    String password;  //密码

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
