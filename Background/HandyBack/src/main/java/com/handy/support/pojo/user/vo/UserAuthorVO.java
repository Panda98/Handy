package com.handy.support.pojo.user.vo;

/**
 * Created by Pan on 2019/4/12.
 */
public class UserAuthorVO {
    private String username;
    private String password;

    public UserAuthorVO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserAuthorVO(){
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
