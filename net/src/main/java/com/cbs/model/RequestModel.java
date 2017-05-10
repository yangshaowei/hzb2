package com.cbs.model;

import com.cbs.Constant.AppConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.UnsupportedEncodingException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/5/8.
 */
public abstract class RequestModel {

    public void sendRequest(String jsonStr, final RequestCallBack requestCallBack){

        OkGo.post(getURL())
                .upJson(jsonStr)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        requestCallBack.onSuccess(s, call, response);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        requestCallBack.onError(call, response, e);
                    }
                });

    }

    public <T> String toJson(T requestBody ){
        Gson gson = new Gson();
        String str = gson.toJson(requestBody);
        return str;
    }

    public abstract void request(RequestCallBack requestCallBack);
//    public abstract <T>T toJson(T requestBody );
    public abstract String getURL();
    public  interface RequestCallBack{
        void onSuccess(String s, Call call, Response response);
        void onError(Call call, Response response, Exception e);
    }

}
