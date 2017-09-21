package com.cbs.hzb.ui.presenters;

import android.content.Context;

import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.utils.BillMessageDbUtil;
import com.cbs.hzb.ui.adapt.BillAdapter;
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
        return BillList.getBillList();
    }

    @Override
    public void notifyDataChange(Context context, BillAdapter adapter) {
        BillList.getBillList().cacheBillList(context);
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载缓存
     */
    public void startCacheLoading(Context context, BillList.SyncLoadBillLisetner syncLoadBillLisetner) {
        BillList.getBillList().loadBillListFromNet(context, syncLoadBillLisetner);
    }
}
