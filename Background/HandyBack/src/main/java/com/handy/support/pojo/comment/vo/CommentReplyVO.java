package com.handy.support.pojo.comment.vo;

import java.util.Date;

public class CommentReplyVO {
    private Integer replyId;
    private String replyContent;
    private Integer userId;
    private Integer commentId;
    private Date updateTime;
    private int toReplyId;
    private String nickName;
    private String toReplyNickName;
    private String userPic;

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getToReplyId() {
        return toReplyId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public String getToReplyNickName() {
        return toReplyNickName;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public void setToReplyId(int toReplyId) {
        this.toReplyId = toReplyId;
    }

    public void setToReplyNickName(String toReplyNickName) {
        this.toReplyNickName = toReplyNickName;
    }
}
