package com.handy.support.pojo.user.vo;

import com.handy.support.pojo.user.dto.UserDto;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pan on 2019/5/4.
 */
public class UserModifyVO {
    private int userId;
    private String nickName;
    private String sex;
    private String userPic;
    private String birthday;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public UserDto revert2DTO(){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(this,userDto);
        if(this.sex != null)
            userDto.setSex(this.sex.equals("男")?(byte)1:2);


        try{
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date birthday = format.parse(this.birthday);
            userDto.setBirthday(birthday);
        }catch (Exception e){
            e.printStackTrace();
        }

        return userDto;
    }
}
