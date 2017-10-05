package com.cbs.impl;

import com.cbs.bean.Record;
import com.cbs.domain.AddRecordRequestBody;
import com.cbs.domain.CreatAffairRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class AddRecordAffairImpl extends RequestModel{

    private String account;
    private String password;
    private String affairId;  //事务id标识
    private String cleanerPlanId;  //记录所在的计划id
    private Record record;

    public AddRecordAffairImpl(String account, String password, String affairId, String cleanerPlanId, Record record){
        this.account = account;
        this.password = password;
        this.affairId = affairId;
        this.cleanerPlanId = cleanerPlanId;
        this.record = record;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        AddRecordRequestBody addRecordRequestBody = new AddRecordRequestBody();
        addRecordRequestBody.setAccount(account);
        addRecordRequestBody.setPassword(password);
        addRecordRequestBody.setAffairId(affairId);
        addRecordRequestBody.setCleanerPlanId(cleanerPlanId);
        addRecordRequestBody.setRecord(record);
        String jsonStr = toJson(addRecordRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
