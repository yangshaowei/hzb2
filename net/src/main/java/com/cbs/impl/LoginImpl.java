package com.cbs.impl;

import android.content.Context;

import com.cbs.Constant.AppConstant;
import com.cbs.domain.LoginRequestBody;
import com.cbs.domain.ResponeBody;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class LoginImpl extends RequestModel {

    private String account;
    private String password;
    public LoginImpl(String account, String password){
        this.account = account;
        this.password = password;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        LoginRequestBody loginRequestBody = new LoginRequestBody();
        loginRequestBody.setPassword(password);
        loginRequestBody.setAccount(account);
        String jsonStr = toJson(loginRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return AppConstant.LoginUrl;
    }
}
