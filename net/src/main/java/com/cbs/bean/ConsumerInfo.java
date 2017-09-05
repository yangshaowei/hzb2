package com.cbs.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class ConsumerInfo implements Parcelable {
    private String holdersId = null;
    private String type = null;
    private String sum = null;
    private String time = null;
    private String des = null;

    public ConsumerInfo(){

    }

    protected ConsumerInfo(Parcel in) {
        holdersId = in.readString();
        type = in.readString();
        sum = in.readString();
        time = in.readString();
        des = in.readString();
    }

    public static final Creator<ConsumerInfo> CREATOR = new Creator<ConsumerInfo>() {
        @Override
        public ConsumerInfo createFromParcel(Parcel in) {
            return new ConsumerInfo(in);
        }

        @Override
        public ConsumerInfo[] newArray(int size) {
            return new ConsumerInfo[size];
        }
    };

    public String getDescribe() {
        return des;
    }

    public void setDescribe(String describe) {
        this.des = describe;
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

    public String getHoldersId() {
        return holdersId;
    }

    public void setHoldersId(String holdersId) {
        this.holdersId = holdersId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(holdersId);
        dest.writeString(type);
        dest.writeString(sum);
        dest.writeString(time);
        dest.writeString(des);
    }
}
