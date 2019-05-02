package com.example.handy.core;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.http.HttpHelper;
import com.example.handy.core.prefs.PreferenceHelper;
import com.example.handy.core.vo.LoginView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    public Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData(int uid) {
        return mHttpHelper.getRecommendAlbumListData(uid);
    }

    @Override
    public Observable<BaseResponse<List<RecommendCourseData>>> getRecommendCourseListData(int uid, int currentPage, int n) {
        return mHttpHelper.getRecommendCourseListData(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<FollowData>>> getFollowDataList(int uid, int currentPage, int n) {
        return mHttpHelper.getFollowDataList(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<CourseDetailData>> getCourseDetail(int courseId) {
        return mHttpHelper.getCourseDetail(courseId);
    }

    @Override
    public Observable<BaseResponse> uploadCourse(Map<String, RequestBody> partMap, MultipartBody.Part... files) {
        return mHttpHelper.uploadCourse(partMap, files);
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

    @Override
    public boolean getNoImageState() {
        return mPreferenceHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean b) {
        mPreferenceHelper.setNoImageState(b);
    }


}
