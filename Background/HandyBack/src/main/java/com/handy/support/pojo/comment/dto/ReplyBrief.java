package com.handy.support.pojo.comment.dto;

import java.util.Date;

public class ReplyBrief {
    private Integer replyId;
    private String replyContent;
    private Integer userId;
    private Integer commentId;
    private Integer toReplyId;
    private Date updateTime;
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public void setToReplyId(Integer toReplyId) {
        this.toReplyId = toReplyId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getToReplyId() {
        return toReplyId;
    }
}
