package com.cbs.impl;

import com.cbs.domain.DelectBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class DelectBillImpl extends RequestModel {

    private String account;
    private String password;
    private String billId;

    public DelectBillImpl(String account, String password, String billId){
        this.account = account;
        this.password = password;
        this.billId = billId;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        DelectBillRequestBody delectBillRequestBody = new DelectBillRequestBody();
        delectBillRequestBody.setAccount(account);
        delectBillRequestBody.setPassword(password);
        delectBillRequestBody.setPassword(billId);
        String jsonStr = toJson(delectBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
