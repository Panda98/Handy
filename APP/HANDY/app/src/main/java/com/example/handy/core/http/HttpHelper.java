package com.example.handy.core.http;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumListData;
import com.example.handy.core.bean.RecommendCourseListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpHelper {
    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 项目类别数据
     */
    Observable<BaseResponse<LoginData>> getLoginData(String username, String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param rePassword re password
     * @return 登陆数据
     */
    Observable<BaseResponse<LoginData>> getRegisterData(String username, String password, String rePassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 热门推荐
     * http://www.wanandroid.com/banner/json
     *
     * @return 轮播图数据
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 推荐专辑
     * http://www.wanandroid.com/album/recommend
     *
     * @return 专辑列表
     */
    Observable<BaseResponse<List<RecommendAlbumListData>>> getRecommendAlbumListData();

    /**
     * 推荐教程
     * http://www.wanandroid.com/course/recommend
     *
     * @return 教程列表
     */
    Observable<BaseResponse<List<RecommendCourseListData>>> getRecommendCourseListData();
}
