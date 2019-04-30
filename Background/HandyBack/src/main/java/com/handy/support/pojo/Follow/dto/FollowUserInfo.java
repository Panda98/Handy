package com.handy.support.pojo.Follow.dto;


public class FollowUserInfo extends FollowBrief{
    UserBrief user;

    public UserBrief getFollowUser() {
        return user;
    }

    public void setFollowUser(UserBrief followUser) {
        this.user = followUser;
    }

}
