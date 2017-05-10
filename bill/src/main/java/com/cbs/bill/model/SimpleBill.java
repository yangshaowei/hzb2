package com.cbs.bill.model;

import android.content.Context;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class SimpleBill extends ShareBill{

    private String dayConsume;
    private String monConsume;
    private String allConsume;
    private String title;
    private List<ConsumerInfo> consumerInfos;

    public String getDayConsume() {
        return dayConsume;
    }

    public void setDayConsume(String dayConsume) {
        this.dayConsume = dayConsume;
    }

    public String getMonConsume() {
        return monConsume;
    }

    public void setMonConsume(String monConsume) {
        this.monConsume = monConsume;
    }

    public String getAllConsume() {
        return allConsume;
    }

    public void setAllConsume(String allConsume) {
        this.allConsume = allConsume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ConsumerInfo> getConsumerInfos() {
        return consumerInfos;
    }

    public void setConsumerInfos(List<ConsumerInfo> consumerInfos) {
        this.consumerInfos = consumerInfos;
    }
}
