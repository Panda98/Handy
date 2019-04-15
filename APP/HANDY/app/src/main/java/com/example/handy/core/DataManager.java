package com.example.handy.core;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumListData;
import com.example.handy.core.bean.RecommendCourseListData;
import com.example.handy.core.http.HttpHelper;
import com.example.handy.core.prefs.PreferenceHelper;
import com.example.handy.core.vo.LoginView;

import java.util.List;

import io.reactivex.Observable;

public class DataManager implements HttpHelper, PreferenceHelper {

    private HttpHelper mHttpHelper;
    private PreferenceHelper mPreferenceHelper;

    public DataManager(HttpHelper mHttpHelper, PreferenceHelper preferencesHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mPreferenceHelper = preferencesHelper;
    }

    /**
     *  登录注册相关
     */
    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(LoginView loginView) {
        return mHttpHelper.getLoginData(loginView);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(LoginView loginView) {
        return mHttpHelper.getRegisterData(loginView);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mHttpHelper.logout();
    }

    /*
       首页相关
    */

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mHttpHelper.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<RecommendAlbumListData>>> getRecommendAlbumListData() {
        return mHttpHelper.getRecommendAlbumListData();
    }

    @Override
    public Observable<BaseResponse<List<RecommendCourseListData>>> getRecommendCourseListData() {
        return mHttpHelper.getRecommendCourseListData();
    }


    /*
       Preference
    */
    @Override
    public void setLoginAccount(int account) {
        mPreferenceHelper.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        mPreferenceHelper.setLoginPassword(password);
    }

    @Override
    public int getLoginAccount() {
        return mPreferenceHelper.getLoginAccount();
    }

    @Override
    public String getLoginPassword() {
        return mPreferenceHelper.getLoginPassword();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferenceHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferenceHelper.getLoginStatus();
    }

    // main
    @Override
    public void setCurrentPage(int position) {
        mPreferenceHelper.setCurrentPage(position);
    }

    @Override
    public int getCurrentPage() {
        return mPreferenceHelper.getCurrentPage();
    }


}
