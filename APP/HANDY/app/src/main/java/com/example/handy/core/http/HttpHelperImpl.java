package com.example.handy.core.http;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumListData;
import com.example.handy.core.bean.RecommendCourseListData;
import com.example.handy.core.http.api.Apis;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mApis.getLoginData(username, password);
    }

    @Override
    public Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String repassword) {
        return mApis.getRegisterData(username, password, repassword);
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
    public Observable<BaseResponse<List<RecommendAlbumListData>>> getRecommendAlbumListData() {
        return mApis.getRecommendAlbumListData();
    }

    @Override
    public Observable<BaseResponse<List<RecommendCourseListData>>> getRecommendCourseListData() {
        return mApis.getRecommendCourseListData();
    }
}
