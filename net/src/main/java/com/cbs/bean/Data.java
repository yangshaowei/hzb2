package com.cbs.bean;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class Data {
    private BillItem billItem;
    private List<BillItem> billItems;

    //sign字段
    private String id;
    private String timestamp;
    private String sign;

    //事务字段
    private String affairId;
    private String cleanerPlanId;
    private CleanerPlan cleanerPlan;

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

    public CleanerPlan getCleanerPlan() {
        return cleanerPlan;
    }

    public void setCleanerPlan(CleanerPlan cleanerPlan) {
        this.cleanerPlan = cleanerPlan;
    }

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    public BillItem getBillItem() {
        return billItem;
    }

    public void setBillItem(BillItem billItem) {
        this.billItem = billItem;
    }
}
