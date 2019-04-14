package com.handy.support.pojo.user.vo;

/**
 * 用户认证信息的表现层
 * Created by Pan on 2019/4/12.
 */
public class UserAuthVO {
    private String username;
    private String password;

    public UserAuthVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserAuthVO(){
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
