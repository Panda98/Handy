package com.handy.support.utils;

/**
 * Created by joanie on 2019/4/12.
 */
public enum ReturnCodeImpl implements IReturnCode {

    USER_NOT_EXIST(10000, "该账号不存在!"),
    WRONG_PASSWORD(10001, "您输入的密码不正确!"),
    REGISTER_CODE_ERROR(10002, "验证码错误!"),
    USER_LOCK(10003, "该账号已被冻结!");

    /**
     * 状态码
     */
    private Integer errorCode;

    /**
     * 消息
     */
    private String errorMsg;

    /**
     * 数据包
     */
    private String data;

    ReturnCodeImpl(Integer errorCode, String errorMsg, String data) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getData() {
        return data;
    }
}
