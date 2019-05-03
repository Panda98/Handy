package com.handy.support.pojo.comment.dto;

import com.handy.support.pojo.Follow.dto.UserBrief;


public class ReplyUserDTO extends ReplyBrief {
    private UserBrief courseUser;//课程发布者的用户信息
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

    public UserBrief getCourseUser() {
        return courseUser;
    }

    public void setCourseUser(UserBrief courseUser) {
        this.courseUser = courseUser;
    }
}
