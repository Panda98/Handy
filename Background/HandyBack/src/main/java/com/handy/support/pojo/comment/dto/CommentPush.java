package com.handy.support.pojo.comment.dto;

import com.handy.support.entity.Comment;
import com.handy.support.pojo.comment.vo.ComPush;

public class CommentPush {
    private Comment comment;
    public CommentPush(){
        comment=new Comment();
    }

    public void setComment(ComPush comment) {
        this.comment.setCommentContent(comment.getContent());
        this.comment.setCourseId(comment.getCourse_id());
        this.comment.setUserId(comment.getUid());
    }

    public Comment getComment() {
        return comment;
    }
}
