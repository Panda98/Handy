package com.handy.support.pojo.comment.vo;

import com.handy.support.entity.Comment;
import com.handy.support.pojo.comment.dto.CommentDTO;

import java.util.Date;

public class CourseCommentVO {
    private Integer commentId;
    private String commentContent;
    private Integer userId;
    private Integer courseId;
    private Date updateTime;
    private String nickName;
    private String userPic;
public CourseCommentVO(){}
public CourseCommentVO(CommentDTO dto){
    this.commentContent=dto.getComment().getCommentContent();
    this.commentId=dto.getComment().getCommentId();
    this.courseId=dto.getComment().getCourseId();
    this.nickName=dto.getUser().getNickName();
    this.updateTime=dto.getComment().getUpdateTime();
    this.userPic=dto.getUser().getUserPic();
    this.userId=dto.getUser().getUserId();
}
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getNickName() {
        return nickName;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
