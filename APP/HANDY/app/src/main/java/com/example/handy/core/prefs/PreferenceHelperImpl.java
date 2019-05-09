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
    public void setLoginAccount(int account) {
        mPreferences.edit().putInt(Constants.ACCOUNT, account).apply();
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferences.edit().putString(Constants.PASSWORD, password).apply();
    }

    @Override
    public int getLoginAccount() {
        return mPreferences.getInt(Constants.ACCOUNT,0);
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

    @Override
    public void setCurrentPage(int position) {
        mPreferences.edit().putInt(Constants.CURRENT_PAGE, position).apply();
    }

    @Override
    public int getCurrentPage() {
        return mPreferences.getInt(Constants.CURRENT_PAGE, 0);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferences.getBoolean(Constants.NO_IMAGE_STATE, false);
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferences.edit().putBoolean(Constants.NO_IMAGE_STATE, b).apply();
    }
}
