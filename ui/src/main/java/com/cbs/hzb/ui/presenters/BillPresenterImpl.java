package com.cbs.hzb.ui.presenters;

import android.content.Context;

import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.ui.contracts.BillContracts;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BillPresenterImpl implements BillContracts.Presenter{

    @Override
    public void itemClick(Context context, SimpleBill simpleBill) {

    }

    @Override
    public BillList getBillList() {
        BillList bills = new BillList();
        return bills;
    }
}
