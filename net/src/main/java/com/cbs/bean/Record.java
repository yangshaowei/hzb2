package com.cbs.bean;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class Record {

    private int recordId;
    private String holdersId;
    private String time;
    private int star;
    private List<Commen> commens;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getHoldersId() {
        return holdersId;
    }

    public void setHoldersId(String holdersId) {
        this.holdersId = holdersId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public List<Commen> getCommens() {
        return commens;
    }

    public void setCommens(List<Commen> commens) {
        this.commens = commens;
    }
}
