package com.handy.support.utils.status;

/**
 * Created by joanie on 2019/4/13.
 */
public class ReturnCode {

    private ReturnStatus<String,String> errorCode;
    private ReturnStatus<String,String> errorMsg;
    private ReturnStatus<String,String> data;

    public ReturnCode() {
    }

    public ReturnCode(ReturnStatus<String, String> errorCode, ReturnStatus<String, String> errorMsg, ReturnStatus<String, String> data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ReturnStatus<String, String> getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer code) {
        this.errorCode=new ReturnStatus<String,String>("errorCode",code.toString());
    }

    public ReturnStatus<String, String> getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String msg) {
        this.errorMsg=new ReturnStatus<String,String>("errorMsg",msg);
    }

    public ReturnStatus<String, String> getData() {
        return data;
    }

    public void setData(String d) {
        this.data=new ReturnStatus<String,String>("data",d);
    }

}
