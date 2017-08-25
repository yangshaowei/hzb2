package com.cbs.bean;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class Data {
    private BillItem billItem;
    private List<BillItem> billItems;

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
