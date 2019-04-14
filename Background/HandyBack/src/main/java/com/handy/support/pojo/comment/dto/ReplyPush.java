package com.handy.support.pojo.comment.dto;

import com.handy.support.entity.CommentReply;
import com.handy.support.pojo.comment.vo.RepComReq;

public class ReplyPush {
    CommentReply reply;
    public void setReply(RepComReq req){
        this.reply.setCommentId(req.getComment_id());
        this.reply.setReplyContent(req.getContent());
        this.reply.setToReplyId(req.getTo_reply_id());
        this.reply.setUserId(req.getUid());
    }
    public CommentReply getReply() {
        return reply;
    }

    public void setReply(CommentReply reply) {
        this.reply = reply;
    }
}
