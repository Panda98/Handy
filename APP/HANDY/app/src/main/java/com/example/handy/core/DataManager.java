package com.example.handy.core;

import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CommentReplyData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.core.http.HttpHelper;
import com.example.handy.core.prefs.PreferenceHelper;
import com.example.handy.core.vo.LoginView;
import com.example.handy.core.vo.PostCommentView;
import com.example.handy.core.vo.ReplyCommentView;

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

    @Override
    public Observable<BaseResponse<UserInfoData>> getUserInfo(int uid) {
        return mHttpHelper.getUserInfo(uid);
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
    public Observable<BaseResponse<List<CourseData>>> getRecommendCourseListData(int uid, int currentPage, int n) {
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
    public Observable<BaseResponse<Boolean>> follow(int uid, int followId) {
        return mHttpHelper.follow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> unFollow(int uid, int followId) {
        return mHttpHelper.unFollow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> isFollow(int uid, int followId) {
        return mHttpHelper.isFollow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getUserPrivateAlbumList(int userId) {
        return mHttpHelper.getUserPrivateAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getUserSharedAlbumList(int userId) {
        return mHttpHelper.getUserSharedAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getCollectAlbumList(int userId) {
        return mHttpHelper.getCollectAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse> collectCourse(int courseId, int albumId) {
        return mHttpHelper.collectCourse(courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> unCollectCourse(int courseId, int albumId) {
        return mHttpHelper.unCollectCourse(courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> isCollectCourse(int userId, int courseId) {
        return mHttpHelper.isCollectCourse(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> like(int userId, int courseId) {
        return mHttpHelper.like(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> unLike(int userId, int courseId) {
        return mHttpHelper.unLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> isLike(int userId, int courseId) {
        return mHttpHelper.isLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getUserPublishCourse(int userId, int currentPage, int n) {
        return mHttpHelper.getUserPublishCourse(userId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse> uploadCourse(PublishCourseData publishCourseData) {
        return mHttpHelper.uploadCourse(publishCourseData);
    }

    @Override
    public Observable<BaseResponse<String>> uploadImage(MultipartBody.Part file) {
        return mHttpHelper.uploadImage(file);
    }


    @Override
    public Observable<BaseResponse<Boolean>> postComment(PostCommentView commentView) {
        return mHttpHelper.postComment(commentView);
    }

    @Override
    public Observable<BaseResponse<Boolean>> postCommentReply(ReplyCommentView replyCommentView) {
        return mHttpHelper.postCommentReply(replyCommentView);
    }

    @Override
    public Observable<BaseResponse<List<CommentData>>> getComment(int course_id, int currentPage, int n) {
        return mHttpHelper.getComment(course_id, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<CommentReplyData>> getCommentReply(int course_id, int currentPage, int n) {
        return mHttpHelper.getCommentReply(course_id, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getAlbumCourseData(int uid, int albumId, int currentPage, int n) {
        return mHttpHelper.getAlbumCourseData(uid, albumId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<AlbumCoverData>> getAlbumCoverData(int albumId) {
        return mHttpHelper.getAlbumCoverData(albumId);
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
