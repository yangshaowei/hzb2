package com.cbs.domain;

import com.cbs.bean.BillItem;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class GetSignBody {

    private String account = null;
    private String password = null;
    private String id = null;
    private String timestamp = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
