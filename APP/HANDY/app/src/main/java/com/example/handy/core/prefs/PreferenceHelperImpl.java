package com.example.handy.core.prefs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;

import javax.inject.Inject;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public class PreferenceHelperImpl  implements PreferenceHelper {

    private final SharedPreferences mPreferences;

    @Inject
    PreferenceHelperImpl() {

        mPreferences = HandyAPP.getInstance().getSharedPreferences(Constants.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }

    @Override
    public void setLoginAccount(String account) {
        mPreferences.edit().putString(Constants.ACCOUNT, account).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(Constants.PASSWORD, password).apply();
    }

    @Override
    public String getLoginAccount() {
        return mPreferences.getString(Constants.ACCOUNT, "");
    }

    @Override
    public String getLoginPassword() {
        return mPreferences.getString(Constants.PASSWORD, "");
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferences.edit().putBoolean(Constants.LOGIN_STATUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferences.getBoolean(Constants.LOGIN_STATUS, false);
    }
}
