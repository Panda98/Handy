package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.comment.vo.CommentBrief;
import com.handy.support.pojo.comment.vo.ReplyBrief;
import com.handy.support.pojo.comment.vo.ReplyUserVO;

public class ReplyDTO extends ReplyUserVO implements Comparable<ReplyDTO>{
    private CourseBrief course;
    private CommentBrief comment;
    private ReplyBrief toReply;

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
