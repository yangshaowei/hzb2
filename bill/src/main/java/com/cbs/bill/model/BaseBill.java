package com.cbs.bill.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BaseBill implements Parcelable {
    public String id;
    public String holdersId;  //持有者,以逗号分隔
    public Boolean isBalance;  //是否结算
    public String creatTime;  //创建时间

    public BaseBill(){

    }

    protected BaseBill(Parcel in) {
        id = in.readString();
        holdersId = in.readString();
        creatTime = in.readString();
    }

    public static final Creator<BaseBill> CREATOR = new Creator<BaseBill>() {
        @Override
        public BaseBill createFromParcel(Parcel in) {
            return new BaseBill(in);
        }

        @Override
        public BaseBill[] newArray(int size) {
            return new BaseBill[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoldersId() {
        return holdersId;
    }

    public void setHoldersId(String holdersId) {
        this.holdersId = holdersId;
    }

    public Boolean getBalance() {
        return isBalance;
    }

    public void setBalance(Boolean balance) {
        isBalance = balance;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(holdersId);
        dest.writeString(creatTime);
    }
}
