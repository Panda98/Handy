package com.example.handy.core.http;

import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.http.api.Apis;
import com.example.handy.core.vo.LoginView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 对外隐藏进行网络请求的实现细节
 *
 * @author wangziang
 * @date 2019/04/07
 */
public class HttpHelperImpl implements HttpHelper {

    private Apis mApis;

    @Inject
    HttpHelperImpl(Apis mApis) {
        this.mApis = mApis;
    }

    @Override
    public Observable<BaseResponse<LoginData>> getLoginData(LoginView loginView) {
        return mApis.getLoginData(loginView);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(LoginView loginView) {
        return mApis.getRegisterData(loginView);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApis.logout();
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApis.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData(int uid) {
        return mApis.getRecommendAlbumListData(uid);
    }

    @Override
    public Observable<BaseResponse<List<RecommendCourseData>>> getRecommendCourseListData(int uid, int currentPage, int n) {
        return mApis.getRecommendCourseListData(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<FollowData>>> getFollowDataList(int uid, int currentPage, int n) {
        return mApis.getFollowDataList(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<CourseDetailData>> getCourseDetail(int courseId) {
        return mApis.getCourseDetail(courseId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> follow(int uid, int followId) {
        return mApis.follow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> unFollow(int uid, int followId) {
        return mApis.unFollow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumListData>>> getUserAlbumList(int userId) {
        return mApis.getUserAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumListData>>> getCollectAlbumList(int userId) {
        return mApis.getCollectAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse> collect(int courseId, int albumId) {
        return mApis.collect(courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> unCollect(int courseId, int albumId) {
        return mApis.unCollect(courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> isCollect(int userId, int courseId) {
        return mApis.isCollect(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> like(int userId, int courseId) {
        return mApis.like(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> unLike(int userId, int courseId) {
        return mApis.unLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> isLike(int userId, int courseId) {
        return mApis.isLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse<List<RecommendCourseData>>> getUserPublishCourse(int userId, int currentPage, int n) {
        return mApis.getUserPublishCourse(userId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse> uploadCourse(Map<String, RequestBody> partMap, MultipartBody.Part... files) {
        return mApis.uploadCourse(partMap, files);
    }
}
