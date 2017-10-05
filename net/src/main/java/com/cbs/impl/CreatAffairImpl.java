package com.cbs.impl;

import com.cbs.Constant.AppConstant;
import com.cbs.bean.BillItem;
import com.cbs.domain.CreatAffairRequestBody;
import com.cbs.domain.CreatBillRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class CreatAffairImpl extends RequestModel{

    private String account;
    private String password;
    private String sign;
    public CreatAffairImpl(String account, String password, String sign){
        this.account = account;
        this.password = password;
        this.sign = sign;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        CreatAffairRequestBody creatAffairRequestBody = new CreatAffairRequestBody();
        creatAffairRequestBody.setAccount(account);
        creatAffairRequestBody.setPassword(password);
        creatAffairRequestBody.setSign(sign);
        String jsonStr = toJson(creatAffairRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
