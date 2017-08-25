package com.cbs.domain;

import com.cbs.bean.Data;

/**
 * Created by yangshaowei on 2017/5/9.
 */

public class ResponeData {

    private String flag;
    private Data data;
    private String errorCode;
    private String errorString;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
