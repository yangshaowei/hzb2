package com.cbs.hzb.ui.presenters;

import android.widget.EditText;

import com.cbs.bean.BillItem;
import com.cbs.bean.ConsumerInfo;
import com.cbs.bean.Detail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangshaowei on 2017/9/5 0005.
 */

public class AddConsumerInfosPresenterImpl {

    public BillItem getModifyBillItem(String holdersId, String des, String sum, String time, String cid){
        ConsumerInfo consumerInfo = new ConsumerInfo();
        consumerInfo.setHoldersId(holdersId);
        consumerInfo.setDescribe(des);
        consumerInfo.setSum(sum);
        consumerInfo.setCid(cid);
        consumerInfo.setTime(time);
        Detail detail = new Detail();
        detail.getConsumerInfos().add(consumerInfo);
        BillItem billItem = new BillItem();
        billItem.setDetail(detail);
        return billItem;
    }

    public void initCurrentTime(EditText editText){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        editText.setText(str);
    }
}
