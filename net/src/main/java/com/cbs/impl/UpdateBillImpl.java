package com.cbs.impl;

import com.cbs.Constant.AppConstant;
import com.cbs.bean.BillItem;
import com.cbs.domain.ResponeBody;
import com.cbs.domain.UpdateBillRequestBody;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class UpdateBillImpl extends RequestModel {

    private String account;
    private String password;
    private String billId;
    private BillItem billItem;

    public UpdateBillImpl(String account, String password, String billId, BillItem billItem){
        this.account = account;
        this.password = password;
        this.billId = billId;
        this.billItem = billItem;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        UpdateBillRequestBody updateBillRequestBody = new UpdateBillRequestBody();
        updateBillRequestBody.setPassword(password);
        updateBillRequestBody.setAccount(account);
        updateBillRequestBody.setBillItem(billItem);
        updateBillRequestBody.setBillId(billId);

        String jsonStr = toJson(updateBillRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return AppConstant.UpdateBillUrl;
    }
}
