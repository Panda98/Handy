package com.example.handy.core.event;

/**
 * @author wangziang
 * @date 2019/04/07
 */

public class LoginEvent {

    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public LoginEvent(boolean isLogin) {

        this.isLogin = isLogin;
    }
}
