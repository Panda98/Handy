package com.handy.support.utils.status;

import com.google.gson.Gson;
import com.handy.support.utils.GsonSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joanie on 2019/4/13.
 */
public class ReturnCode<T> {

    private Integer errorCode;
    private String msg;
    private T data;

    public ReturnCode(Integer errorCode, String msg, T data) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public ReturnCode(T data) {
        this.errorCode = 0;
        this.msg ="";
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String returnHandler(ReturnCode<T> r){
        Map<String,String> map=new HashMap<String,String>();
        map.put("errorCode",r.getErrorCode().toString());
        map.put("errorMsg",r.getMsg());
        Gson gson=GsonSetting.GSON;
        String s=gson.toJson(r);
        map.put("data",s);
        return gson.toJson(map);
    }
}
