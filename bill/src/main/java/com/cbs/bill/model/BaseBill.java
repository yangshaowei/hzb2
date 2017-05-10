package com.cbs.bill.model;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BaseBill {
    private String id;
    private String holdersId;  //持有者,以逗号分隔
    private Boolean isBalance;  //是否结算
    private String creatTime;  //创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoldersId() {
        return holdersId;
    }

    public void setHoldersId(String holdersId) {
        this.holdersId = holdersId;
    }

    public Boolean getBalance() {
        return isBalance;
    }

    public void setBalance(Boolean balance) {
        isBalance = balance;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
}
