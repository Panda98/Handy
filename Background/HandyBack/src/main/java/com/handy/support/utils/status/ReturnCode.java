package com.handy.support.utils.status;

/**
 * Created by joanie on 2019/4/13.
 */
public class ReturnCode {

    private ReturnStatus<String,String> errorCode;
    private ReturnStatus<String,String> errorMsg;
    private ReturnStatus<String,String> data;


    public ReturnCode get(Integer code, String msg, String d){
        ReturnCode returnCode=new ReturnCode();
        returnCode.errorCode=new ReturnStatus<String,String>("errorCode",code.toString());
        returnCode.errorMsg=new ReturnStatus<String,String>("errorMsg",msg);
        returnCode.data=new ReturnStatus<String,String>("data",d);
        return returnCode;
    }
}
