package com.handy.support.pojo.user.vo;

import com.handy.support.pojo.user.dto.UserDto;

import java.util.Date;

/**
 * Created by joanie on 2019/4/11.
 */
public class UserVO {

    private int id;
    private String email;
    private String nickname;
    private String sex;
    private Date birthday;
    private String userPic;
    private int followCount;
    private int fansCount;

    public UserVO(){
        super();
    }
    public UserVO(int id, String email, String nickname, String sex, Date birthday, String userPic, int followCount,int fansCount) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.userPic = userPic;
        this.followCount = followCount;
        this.fansCount = fansCount;
    }
    public UserVO(UserDto dto){
        this.id = dto.getUserId();
        this.email = dto.getEmail();
        this.nickname = dto.getNickName();
        this.sex = dto.getSex() != null?(dto.getSex() == 1?"男":"女"):null;
        this.birthday = dto.getBirthday();
        this.userPic = dto.getUserPic();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }
}
