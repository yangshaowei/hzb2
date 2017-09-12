package com.cbs.bill.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import com.cbs.bean.ConsumerInfo;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class SimpleBill extends ShareBill implements Parcelable {

    private String dayConsume;
    private String monConsume;
    private String allConsume;
    private String title;
    private List<ConsumerInfo> consumerInfos = new ArrayList<>();

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

    public SimpleBill(){

    }

    protected SimpleBill(Parcel in) {
        dayConsume = in.readString();
        monConsume = in.readString();
        allConsume = in.readString();
        title = in.readString();
        id = in.readString();
        holdersId = in.readString();
        creatTime = in.readString();

        int consumerInfosSize = in.readInt();
        for(int i = 0; i < consumerInfosSize; i++){
            ConsumerInfo consumerInfo = new ConsumerInfo();
            consumerInfo.setHoldersId(in.readString());
            consumerInfo.setType(in.readString());
            consumerInfo.setSum(in.readString());
            consumerInfo.setTime(in.readString());
            consumerInfo.setDescribe(in.readString());
            consumerInfo.setCid(in.readString());
            consumerInfos.add(consumerInfo);
        }

        String b = in.readString();
        if(b.equals("true")){
            isBalance = true;
        }else {
            isBalance = false;
        }
    }

    public static final Creator<SimpleBill> CREATOR = new Creator<SimpleBill>() {
        @Override
        public SimpleBill createFromParcel(Parcel in) {
            return new SimpleBill(in);
        }

        @Override
        public SimpleBill[] newArray(int size) {
            return new SimpleBill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayConsume);
        dest.writeString(monConsume);
        dest.writeString(allConsume);
        dest.writeString(title);
        dest.writeString(id);
        dest.writeString(holdersId);
        dest.writeString(creatTime);

        if(consumerInfos == null){
            dest.writeInt(0);
        }else {
            dest.writeInt(consumerInfos.size());
            for (ConsumerInfo c : consumerInfos) {
                dest.writeString(c.getHoldersId());
                dest.writeString(c.getType());
                dest.writeString(c.getSum());
                dest.writeString(c.getTime());
                dest.writeString(c.getDescribe());
                dest.writeString(c.getCid());
            }
        }

        dest.writeString(String.valueOf(isBalance));
    }


}
