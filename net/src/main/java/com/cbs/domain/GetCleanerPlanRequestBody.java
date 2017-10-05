package com.cbs.domain;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class GetCleanerPlanRequestBody {

    private String account;
    private String password;
    private String affairId;

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

    public String getAffairId() {
        return affairId;
    }

    public void setAffairId(String affairId) {
        this.affairId = affairId;
    }
}
