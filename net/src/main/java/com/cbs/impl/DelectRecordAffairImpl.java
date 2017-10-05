package com.cbs.impl;

import com.cbs.bean.Record;
import com.cbs.domain.AddRecordRequestBody;
import com.cbs.domain.DelectRecordRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class DelectRecordAffairImpl extends RequestModel{

    private String account;
    private String password;
    private String affairId;  //事务id标识
    private String cleanerPlanId;  //记录所在的计划id
    private int recordId;

    public DelectRecordAffairImpl(String account, String password, String affairId, String cleanerPlanId, int recordId){
        this.account = account;
        this.password = password;
        this.affairId = affairId;
        this.cleanerPlanId = cleanerPlanId;
        this.recordId = recordId;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        DelectRecordRequestBody delectRecordRequestBody = new DelectRecordRequestBody();
        delectRecordRequestBody.setAccount(account);
        delectRecordRequestBody.setPassword(password);
        delectRecordRequestBody.setAffairId(affairId);
        delectRecordRequestBody.setCleanerPlanId(cleanerPlanId);
        delectRecordRequestBody.setRecordId(recordId);
        String jsonStr = toJson(delectRecordRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
