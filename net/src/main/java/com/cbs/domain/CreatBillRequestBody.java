package com.cbs.domain;

import com.cbs.bean.BillItem;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class CreatBillRequestBody {

    private String account = null;
    private String password = null;
    private BillItem billItem = null;

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

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }
}
