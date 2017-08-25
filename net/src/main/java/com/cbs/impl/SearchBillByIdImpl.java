package com.cbs.impl;

import com.cbs.domain.CreatBillRequestBody;
import com.cbs.domain.SearchBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class SearchBillByIdImpl extends RequestModel {

    private String account;
    private String password;
    private String billId;
    public SearchBillByIdImpl(String account, String password, String billId){
        this.account = account;
        this.password = password;
        this.billId = billId;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        SearchBillRequestBody searchBillRequestBody = new SearchBillRequestBody();
        searchBillRequestBody.setAccount(account);
        searchBillRequestBody.setPassword(password);
        searchBillRequestBody.setBillId(billId);
        String jsonStr = toJson(searchBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
