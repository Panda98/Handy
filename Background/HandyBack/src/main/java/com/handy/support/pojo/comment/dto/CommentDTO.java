package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.Follow.vo.UserBrief;
import com.handy.support.pojo.comment.vo.CommentBrief;

public class CommentDTO extends CommentBrief{
    private UserBrief user;
    private CourseBrief course;

    public void setCourse(CourseBrief course) {
        this.course = course;
    }

    public void setUser(UserBrief user) {
        this.user = user;
    }

    public CourseBrief getCourse() {
        return course;
    }

    public UserBrief getUser() {
        return user;
    }
}
