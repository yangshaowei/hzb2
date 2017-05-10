package com.cbs.domain;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class DelectBillRequestBody {
    private String account;
    private String password;
    private String billId;

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

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }
}
