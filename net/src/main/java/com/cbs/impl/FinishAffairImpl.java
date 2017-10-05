package com.cbs.impl;

import com.cbs.Constant.AppConstant;
import com.cbs.domain.CreatAffairRequestBody;
import com.cbs.domain.FinishAffairRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class FinishAffairImpl extends RequestModel{

    private String account;
    private String password;
    private String affair;
    private String sign;
    public FinishAffairImpl(String account, String password, String affair, String sign){
        this.account = account;
        this.password = password;
        this.affair = affair;
        this.sign = sign;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        FinishAffairRequestBody finishAffairRequestBody = new FinishAffairRequestBody();
        finishAffairRequestBody.setAccount(account);
        finishAffairRequestBody.setPassword(password);
        finishAffairRequestBody.setAffairId(affair);
        finishAffairRequestBody.setSign(sign);
        String jsonStr = toJson(finishAffairRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
