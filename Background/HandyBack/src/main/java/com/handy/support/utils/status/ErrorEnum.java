package com.handy.support.utils.status;



/**
 * Created by joanie on 2019/4/13.
 */
public enum ErrorEnum{
    USER_NOT_EXIST(1000,"该账号不存在!"),
    WRONG_PASSWORD(1001, "您输入的密码不正确!"),
    REGISTER_FAIL(2001,"注册失败！"),
    DATABASE_BUSY(3001,"数据库忙!"),
    PUBLISH_FAIL(4001,"发布失败！"),
    QUERY_FAIL(5001,"查询失败!"),
    COLLECT_FAIL(6001,"收藏失败!"),
    UNCOLLECT_FAIL(6002,"取消收藏!"),
    UPDATE_FAIL(7001,"更新失败!"),
    LIKE_FAIL(8001,"点赞失败"),
    UNLIKE_FAIL(8002,"取消点赞失败!"),
    COMMENT_FAIL(9001,"评论失败!");
    /**
     * 状态码
     */
    private Integer errorCode;

    /**
     * 消息
     */
    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
