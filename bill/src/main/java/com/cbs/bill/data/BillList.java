package com.cbs.bill.data;

import android.content.Context;

import com.cbs.bean.BillItem;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.model.BillMessage;
import com.cbs.common.utils.BillMessageDbUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class BillList extends ArrayList<SimpleBill> {

    //修改，删除，查找账单操作

    private static BillList billList;

    public static BillList getBillList(){
        if(billList == null){
            billList = new BillList();
        }
        return billList;
    }

    public void addSimpleBill(SimpleBill simpleBill){
        add(simpleBill);
    }

    /**
     * 网络，数据库，缓存同步
     * @param context
     */
    public void loadBillListFromNet(Context context){
        BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(context);
        billMessageDbUtil.syncDb(); //从网络更新数据库
        List<BillItem> billItemList = billMessageDbUtil.queryBillItemAll();

        billList.clear();
        for(BillItem billItem : billItemList){
            SimpleBill simpleBill  = new SimpleBill();
            simpleBill.setHoldersId(billItem.getHoldersId());
            simpleBill.setAllConsume(billItem.getDetail().getAllConsume());
            simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
            simpleBill.setDayConsume(billItem.getDetail().getDayConsume());
            simpleBill.setMonConsume(billItem.getDetail().getMonConsume());
            simpleBill.setTitle(billItem.getDetail().getTitle());
            simpleBill.setBalance(billItem.isBalance());
            simpleBill.setCreatTime(billItem.getDetail().getCreateTime());
            simpleBill.setId(billItem.getId());
            billList.add(simpleBill);
        }
    }

    /**
     * 数据库与缓存同步
     * @param context
     */
    public void cacheBillList(Context context){
        BillMessageDbUtil billMessageDbUtil = new BillMessageDbUtil(context);
        List<BillItem> billItemList = billMessageDbUtil.queryBillItemAll();

        billList.clear();
        for(BillItem billItem : billItemList){
            SimpleBill simpleBill  = new SimpleBill();
            simpleBill.setHoldersId(billItem.getHoldersId());
            simpleBill.setAllConsume(billItem.getDetail().getAllConsume());
            simpleBill.setConsumerInfos(billItem.getDetail().getConsumerInfos());
            simpleBill.setDayConsume(billItem.getDetail().getDayConsume());
            simpleBill.setMonConsume(billItem.getDetail().getMonConsume());
            simpleBill.setTitle(billItem.getDetail().getTitle());
            simpleBill.setBalance(billItem.isBalance());
            simpleBill.setCreatTime(billItem.getDetail().getCreateTime());
            simpleBill.setId(billItem.getId());
            billList.add(simpleBill);
        }
    }
}
