package com.cbs.bean;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class Detail {

    private String dayConsume;
    private String monConsume;
    private String allConsume;
    private String title;
    private String createTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<ConsumerInfo> getConsumerInfos() {
        return consumerInfos;
    }

    public void setConsumerInfos(List<ConsumerInfo> consumerInfos) {
        this.consumerInfos = consumerInfos;
    }
}
