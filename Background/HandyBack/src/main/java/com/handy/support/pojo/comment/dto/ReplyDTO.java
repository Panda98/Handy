package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.comment.vo.CommentBrief;
import com.handy.support.pojo.comment.vo.ReplyUserVO;

public class ReplyDTO {
    private CourseBrief course;
    private CommentBrief comment;
    private ReplyUserVO reply;
    public void setComment(CommentBrief comment) {
        this.comment = comment;
    }

    public CourseBrief getCourse() {
        return course;
    }

    public void setCourse(CourseBrief course) {
        this.course = course;
    }

    public CommentBrief getComment() {
        return comment;
    }

    public void setReply(ReplyUserVO reply) {
        this.reply = reply;
    }

    public ReplyUserVO getReply() {
        return reply;
    }
}
