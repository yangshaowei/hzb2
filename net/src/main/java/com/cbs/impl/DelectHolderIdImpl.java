package com.cbs.impl;

import com.cbs.domain.DelectBillRequestBody;
import com.cbs.domain.DelectHolderIdRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class DelectHolderIdImpl extends RequestModel {

    private String account;
    private String password;
    private String billId;
    private String delectId;
    public DelectHolderIdImpl(String account, String password, String billId, String delectId){
        this.account = account;
        this.password = password;
        this.billId = billId;
        this.delectId = delectId;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        DelectHolderIdRequestBody delectBillRequestBody = new DelectHolderIdRequestBody();
        delectBillRequestBody.setAccount(account);
        delectBillRequestBody.setPassword(password);
        delectBillRequestBody.setBillId(billId);
        delectBillRequestBody.setDelectId(delectId);
        String jsonStr = toJson(delectBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
