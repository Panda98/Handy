package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.dto.CourseBrief;
import com.handy.support.pojo.Follow.dto.UserBrief;

public class ReplyDTO extends ReplyUserDTO implements Comparable<ReplyDTO>{
    private CourseBrief course;
    private CommentBrief comment;
    private ReplyBrief toReply;
    private UserBrief user;//回复他人的人

    public UserBrief getUser() {
        return user;
    }

    public void setUser(UserBrief user) {
        this.user = user;
    }

    public ReplyBrief getToReply() {
        return toReply;
    }

    public void setToReply(ReplyBrief toReply) {
        this.toReply = toReply;
    }

    public CourseBrief getCourse() {
        return course;
    }

    public void setCourse(CourseBrief course) {
        this.course = course;
    }

    public void setComment(CommentBrief comment) {
        this.comment = comment;
    }

    public CommentBrief getComment() {
        return comment;
    }

    public int compareTo(ReplyDTO o) {
        if(o.getUpdateTime().after(this.getUpdateTime()))
            return -1;
        else if(o.getUpdateTime().before(this.getUpdateTime()))
            return 1;
        else
            return 0;
    }
}
