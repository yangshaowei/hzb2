package com.cbs.bean;

import com.cbs.bean.Detail;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class BillItem {
    private String pid;
    private String holdersId = null;  //持有者,以逗号分隔
    private boolean isBalance = false; //是否结算
    private Detail detail = null;   //详细描述

    public boolean isBalance() {
        return isBalance;
    }

    public void setBalance(boolean balance) {
        isBalance = balance;
    }

    public String getId() {
        return pid;
    }

    public void setId(String id) {
        this.pid = id;
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
