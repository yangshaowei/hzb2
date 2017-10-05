package com.cbs.impl;

import com.cbs.bean.CleanerPlan;
import com.cbs.bean.Record;
import com.cbs.domain.AddRecordRequestBody;
import com.cbs.domain.ModifyCleanerPlanRequestBody;
import com.cbs.model.RequestModel;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class ModifyCleanerPlanImpl extends RequestModel{

    private String account;
    private String password;
    private String affairId;
    private String cleanerPlanId;
    private CleanerPlan cleanerPlan;

    public ModifyCleanerPlanImpl(String account, String password, String affairId, String cleanerPlanId, CleanerPlan cleanerPlan){
        this.account = account;
        this.password = password;
        this.affairId = affairId;
        this.cleanerPlanId = cleanerPlanId;
        this.cleanerPlan = cleanerPlan;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        ModifyCleanerPlanRequestBody modifyCleanerPlanRequestBody = new ModifyCleanerPlanRequestBody();
        modifyCleanerPlanRequestBody.setAccount(account);
        modifyCleanerPlanRequestBody.setPassword(password);
        modifyCleanerPlanRequestBody.setAffairId(affairId);
        modifyCleanerPlanRequestBody.setCleanerPlanId(cleanerPlanId);
        modifyCleanerPlanRequestBody.setCleanerPlan(cleanerPlan);
        String jsonStr = toJson(modifyCleanerPlanRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return null;
    }
}
