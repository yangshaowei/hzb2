package com.cbs.bill.model;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class ConsumerInfo {
    private String holdersID;  //消费记录者的id
    private String type;  //"in":表示支出，其余待定
    private String sum;  //本次消费金额
    private String time;  //记录时间
    private String describe;  //账单描述

    public String getHoldersID() {
        return holdersID;
    }

    public void setHoldersID(String holdersID) {
        this.holdersID = holdersID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
