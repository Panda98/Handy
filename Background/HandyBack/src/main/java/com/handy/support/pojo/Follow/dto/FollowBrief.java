package com.handy.support.pojo.Follow.dto;

public class FollowBrief {
    private Integer followId;
    private Integer userId;
    private Integer followerId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getFollowerId() {
        return followerId;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }
}
