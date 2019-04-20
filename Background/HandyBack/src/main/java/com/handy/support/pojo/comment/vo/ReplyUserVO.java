package com.handy.support.pojo.comment.vo;

import com.handy.support.entity.CommentReply;
import com.handy.support.pojo.Follow.vo.UserBrief;


public class ReplyUserVO {
    private ReplyBrief reply;
    private UserBrief user;//消息回复者的用户信息
    private UserBrief toReplyUser;//被回复者用户信息
    private UserBrief commentUser;//所在一级评论的用户信息
    public UserBrief getToReplyUser() {
        return toReplyUser;
    }

    public UserBrief getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(UserBrief commentUser) {
        this.commentUser = commentUser;
    }

    public void setToReplyUser(UserBrief toReplyUser) {
        this.toReplyUser = toReplyUser;
    }

    public void setReply(ReplyBrief reply) {
        this.reply = reply;
    }

    public ReplyBrief getReply() {
        return reply;
    }

    public UserBrief getUser() {
        return user;
    }

    public void setUser(UserBrief user) {
        this.user = user;
    }

}
