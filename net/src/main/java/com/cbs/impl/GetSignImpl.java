package com.cbs.impl;

import com.cbs.Constant.AppConstant;
import com.cbs.bean.BillItem;
import com.cbs.domain.CreatBillRequestBody;
import com.cbs.domain.GetSignBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class GetSignImpl extends RequestModel{

    /**
     * 需要申请密钥的id，例如申请账单的密钥，id值为billId。申请事务的密钥，id值为affairId
     */
    private String id = null;
    private String account;
    private String password;
    private String timestamp = null;
    public GetSignImpl(String account, String password, String id, String timestamp){
        this.account = account;
        this.password = password;
        this.id = id;
        this.timestamp = timestamp;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        GetSignBody getSignBody = new GetSignBody();
        getSignBody.setAccount(account);
        getSignBody.setPassword(password);
        getSignBody.setId(id);
        getSignBody.setTimestamp(timestamp);
        String jsonStr = toJson(getSignBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return AppConstant.GetSignUrl;
    }
}
