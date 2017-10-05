package com.cbs.domain;

import com.cbs.bean.CleanerPlan;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class ModifyCleanerPlanRequestBody {

    private String account;
    private String password;
    private String affairId;
    private String cleanerPlanId;
    private CleanerPlan cleanerPlan;

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

    public String getCleanerPlanId() {
        return cleanerPlanId;
    }

    public void setCleanerPlanId(String cleanerPlanId) {
        this.cleanerPlanId = cleanerPlanId;
    }

    public CleanerPlan getCleanerPlan() {
        return cleanerPlan;
    }

    public void setCleanerPlan(CleanerPlan cleanerPlan) {
        this.cleanerPlan = cleanerPlan;
    }
}
