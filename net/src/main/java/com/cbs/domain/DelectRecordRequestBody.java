package com.cbs.domain;

import com.cbs.bean.Record;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class DelectRecordRequestBody {

    private String account;
    private String password;
    private String affairId;  //事务id标识
    private String cleanerPlanId;
    private int recordId;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getCleanerPlanId() {
        return cleanerPlanId;
    }

    public void setCleanerPlanId(String cleanerPlanId) {
        this.cleanerPlanId = cleanerPlanId;
    }

    public String getAffairId() {
        return affairId;
    }

    public void setAffairId(String affairId) {
        this.affairId = affairId;
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
