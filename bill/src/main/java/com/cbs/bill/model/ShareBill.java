package com.cbs.bill.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class ShareBill extends BaseBill implements Parcelable {

    public ShareBill(){

    }

    protected ShareBill(Parcel in) {
    }

    public static final Creator<ShareBill> CREATOR = new Creator<ShareBill>() {
        @Override
        public ShareBill createFromParcel(Parcel in) {
            return new ShareBill(in);
        }

        @Override
        public ShareBill[] newArray(int size) {
            return new ShareBill[size];
        }
    };

    public void shareBillToWetChat(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
