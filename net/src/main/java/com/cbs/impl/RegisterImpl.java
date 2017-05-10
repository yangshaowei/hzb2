package com.cbs.impl;

import android.content.Context;

import com.cbs.Constant.AppConstant;
import com.cbs.domain.RegisterRequestBody;
import com.cbs.domain.ResponeBody;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/8.
 */

public class RegisterImpl extends RequestModel{

    private Context context;
    private String account;
    private String password;
    public RegisterImpl(Context context, String account, String password){
        this.context = context;
        this.account = account;
        this.password = password;
    }

    @Override
    public void request(RequestCallBack requestCallBack) {
        RegisterRequestBody registerRequestBody = new RegisterRequestBody();
        registerRequestBody.setPassword(password);
        registerRequestBody.setAccount(account);
        String jsonStr = toJson(registerRequestBody);
        sendRequest(jsonStr, requestCallBack);
    }

    @Override
    public String getURL() {
        return AppConstant.RegisterUrl;
    }
}
