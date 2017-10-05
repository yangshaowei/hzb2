package com.cbs.bean;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class CleanerPlan {

    private String cleanerPlanId;
    private int type;
    private List<String> holdersIds;
    private int onDuty;
    private List<Record> records;
    private String data;
    private String interval;

    public String getCleanerPlanId() {
        return cleanerPlanId;
    }

    public void setCleanerPlanId(String cleanerPlanId) {
        this.cleanerPlanId = cleanerPlanId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getHoldersIds() {
        return holdersIds;
    }

    public void setHoldersIds(List<String> holdersIds) {
        this.holdersIds = holdersIds;
    }

    public int getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(int onDuty) {
        this.onDuty = onDuty;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
