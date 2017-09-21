package com.cbs.bill.model;

/**
 * Created by yangshaowei on 2017/9/13.
 */

public class BalanceData{
    private String holderId;
    private String out; //已支出金额
    private String in;  //结算结果

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }
}
