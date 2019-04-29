package com.handy.support.pojo.comment.dto;

import java.util.Date;

public class CommentBrief {
    private Integer commentId;
    private String commentContent;
    private Integer userId;
    private Integer courseId;
    private Date updateTime;

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
