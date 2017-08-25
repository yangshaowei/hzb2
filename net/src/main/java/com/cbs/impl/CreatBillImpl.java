package com.cbs.impl;

import com.cbs.Constant.AppConstant;
import com.cbs.bean.BillItem;
import com.cbs.domain.CreatBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class CreatBillImpl extends RequestModel{

    private String account;
    private String password;
    private BillItem billItem;
    public CreatBillImpl(String account, String password, BillItem billItem){
        this.account = account;
        this.password = password;
        this.billItem = billItem;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        CreatBillRequestBody creatBillRequestBody = new CreatBillRequestBody();
        creatBillRequestBody.setAccount(account);
        creatBillRequestBody.setPassword(password);
        creatBillRequestBody.setBillItem(billItem);
        String jsonStr = toJson(creatBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return AppConstant.CreatBillUrl;
    }
}
