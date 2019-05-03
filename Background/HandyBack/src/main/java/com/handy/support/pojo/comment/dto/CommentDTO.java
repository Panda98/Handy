package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.dto.CourseBrief;
import com.handy.support.pojo.Follow.dto.UserBrief;

public class CommentDTO extends CommentBrief{
    private UserBrief user;
    private CourseBrief course;
private int replyNum;

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

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
