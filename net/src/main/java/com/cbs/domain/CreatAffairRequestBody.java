package com.cbs.domain;

import com.cbs.bean.BillItem;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class CreatAffairRequestBody {

    private String account;
    private String password;
    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

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
