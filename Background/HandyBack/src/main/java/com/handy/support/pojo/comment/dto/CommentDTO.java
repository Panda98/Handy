package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.Follow.vo.UserBrief;
import com.handy.support.pojo.comment.vo.CommentBrief;

public class CommentDTO {
    private UserBrief user;
    private CourseBrief course;
    private CommentBrief comment;

    public void setCourse(CourseBrief course) {
        this.course = course;
    }

    public void setUser(UserBrief user) {
        this.user = user;
    }

    public CommentBrief getComment() {
        return comment;
    }

    public CourseBrief getCourse() {
        return course;
    }

    public UserBrief getUser() {
        return user;
    }

    public void setComment(CommentBrief comment) {
        this.comment = comment;
    }
}
