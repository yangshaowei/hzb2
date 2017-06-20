package com.cbs.bill.data;

import com.cbs.bill.model.SimpleBill;

import java.util.ArrayList;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BillList extends ArrayList<SimpleBill> {

    //修改，删除，查找账单操作

    public void addSimpleBill(SimpleBill simpleBill){
        add(simpleBill);
    }
}
