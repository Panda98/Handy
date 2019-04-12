package com.handy.support.pojo.vo;

import com.handy.support.pojo.dto.UserDto;

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

    public UserVO(int id, String email, String nickname, String sex, Date birthday, String userPic) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.sex = sex;
        this.birthday = birthday;
        this.userPic = userPic;
    }
    public UserVO(UserDto dto){
        this.id = dto.getUserId();
        this.email = dto.getEmail();
        this.nickname = dto.getNickName();
        this.sex = dto.getSex() == 1?"男":"女";
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
}
