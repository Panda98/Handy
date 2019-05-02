package com.handy.support.pojo.Follow.vo;

import com.handy.support.pojo.Follow.dto.FollowUserInfo;

public class FollowUsersVO {
    private Integer userId;
    private String nickName;
    private String userPic;
public FollowUsersVO(FollowUserInfo dto){
    userId=dto.getFollowUser().getUserId();
    nickName=dto.getFollowUser().getNickName();
    userPic=dto.getFollowUser().getUserPic();
}
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
