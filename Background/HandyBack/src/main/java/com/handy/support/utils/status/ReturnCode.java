package com.handy.support.utils.status;

import com.google.gson.Gson;
import com.handy.support.utils.GsonSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joanie on 2019/4/13.
 */
public class ReturnCode<T> {
    private ErrorEnum errorEnum;
    private T data;

    public ReturnCode(ErrorEnum errorEnum, T data) {
        this.errorEnum=errorEnum;
        this.data = data;
    }

    public ReturnCode(T data) {
        this.errorEnum=ErrorEnum.SUCCESS;
        this.data = data;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ReturnCode() {
    }

    public String returnHandler(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("errorCode",this.getErrorEnum().getErrorCode());
        map.put("errorMsg",this.getErrorEnum().getErrorMsg());
        Gson gson=GsonSetting.GSON;
        map.put("data",this.getData());
        return gson.toJson(map);
    }
}
