package com.cbs.bean;

import com.cbs.bean.Detail;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class BillItem {
    private String id;
    private String holdersId;  //持有者,以逗号分隔
    private Boolean isBalance; //是否结算
    private Detail detail;   //详细描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getBalance() {
        return isBalance;
    }

    public void setBalance(Boolean balance) {
        isBalance = balance;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getHoldersId() {
        return holdersId;
    }

    public void setHoldersId(String holdersId) {
        this.holdersId = holdersId;
    }
}
