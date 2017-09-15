package com.cbs.bill.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshaowei on 2017/9/13
 */

public class AdavancedBill extends SimpleBill{
    private List<BalanceData> balanceDataList = new ArrayList<>();
    private int holderNum;
    private String allSum;
    private String averageConsume;

    public int getHolderNum() {
        return holderNum;
    }

    public void setHolderNum(int holderNum) {
        this.holderNum = holderNum;
    }

    public String getAllSum() {
        return allSum;
    }

    public void setAllSum(String allSum) {
        this.allSum = allSum;
    }

    public List<BalanceData> getBalanceDataList() {
        return balanceDataList;
    }

    public void setBalanceDataList(List<BalanceData> balanceDataList) {
        this.balanceDataList = balanceDataList;
    }

    public String getAverageConsume() {
        return averageConsume;
    }

    public void setAverageConsume(String averageConsume) {
        this.averageConsume = averageConsume;
    }
}
