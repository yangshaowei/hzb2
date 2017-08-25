package com.cbs.impl;

import com.cbs.domain.SearchBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class SearchBillAllImpl extends RequestModel {

    private String account;
    private String password;
    public SearchBillAllImpl(String account, String password){
        this.account = account;
        this.password = password;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        SearchBillRequestBody searchBillRequestBody = new SearchBillRequestBody();
        searchBillRequestBody.setAccount(account);
        searchBillRequestBody.setPassword(password);
        String jsonStr = toJson(searchBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
