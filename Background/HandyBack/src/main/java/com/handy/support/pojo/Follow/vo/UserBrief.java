package com.handy.support.pojo.Follow.vo;

public class UserBrief {
    private int userId;
    private String nickName;
    private String userPic;

    public int getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}
