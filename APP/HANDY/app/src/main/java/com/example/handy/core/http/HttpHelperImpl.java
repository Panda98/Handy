package com.example.handy.core.http;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
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
    public Observable<BaseResponse> uploadCourse(Map<String, RequestBody> partMap, MultipartBody.Part... files) {
        return mApis.uploadCourse(partMap, files);
    }
}
