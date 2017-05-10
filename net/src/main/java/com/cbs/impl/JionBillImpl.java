package com.cbs.impl;

import com.cbs.domain.CreatBillRequestBody;
import com.cbs.domain.JoinBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class JionBillImpl extends RequestModel {

    private String account;
    private String password;
    private String billId;
    public JionBillImpl(String account, String password, String billId){
        this.account = account;
        this.password = password;
        this.billId = billId;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        JoinBillRequestBody joinBillRequestBody = new JoinBillRequestBody();
        joinBillRequestBody.setAccount(account);
        joinBillRequestBody.setPassword(password);
        joinBillRequestBody.setBillId(billId);
        String jsonStr = toJson(joinBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
