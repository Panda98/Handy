package com.handy.support.pojo.comment.vo;

import java.util.Date;

public class CommentReplyUserFull {
    private int userId;
    private String userNickName;
    private String userPic;
    private int toUserId;
    private String toUserNickName;
    private String toUserPic;
    private Integer replyId;
    private String replyContent;
    private Integer commentId;
    private Integer toReplyId;
    private Date updateTime;
public CommentReplyUserFull(){}
public CommentReplyUserFull(ReplyUserVO re){
    this.commentId=re.getReply().getCommentId();
    this.replyContent=re.getReply().getReplyContent();
    this.replyId=re.getReply().getReplyId();
    this.toReplyId=re.getReply().getToReplyId();
    this.toUserId=re.getToReplyUser().getUserId();
    this.toUserNickName=re.getToReplyUser().getNickName();
    this.toUserPic=re.getToReplyUser().getUserPic();
    this.updateTime=re.getReply().getUpdateTime();
    this.userId=re.getUser().getUserId();
    this.userNickName=re.getUser().getNickName();
    this.userPic=re.getUser().getUserPic();

}
    public Integer getToReplyId() {
        return toReplyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setToReplyId(Integer toReplyId) {
        this.toReplyId = toReplyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserPic() {
        return userPic;
    }

    public int getToUserId() {
        return toUserId;
    }

    public String getToUserNickName() {
        return toUserNickName;
    }

    public String getToUserPic() {
        return toUserPic;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public void setToUserNickName(String toUserNickName) {
        this.toUserNickName = toUserNickName;
    }

    public void setToUserPic(String toUserPic) {
        this.toUserPic = toUserPic;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
