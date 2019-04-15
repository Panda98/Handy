package com.example.handy.core.http.api;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumListData;
import com.example.handy.core.bean.RecommendCourseListData;
import com.example.handy.core.vo.LoginView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public interface Apis {
    String HOST = "http://106.13.106.249:8080/";

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param loginData name password
     * @return 登陆数据
     */
    @POST("user/login")
    Observable<BaseResponse<LoginData>> getLoginData(@Body LoginView loginData);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param loginData user name password
     * @return 注册数据
     */
    @POST("user/regist")
    Observable<BaseResponse<LoginData>> getRegisterData(@Body LoginView loginData);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     *
     * @return 登陆数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 热门推荐
     * http://www.wanandroid.com/banner/json
     *
     * @return 轮播数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 推荐专辑
     * http://www.wanandroid.com/album/recommend
     *
     * @return 专辑列表
     */
    @GET("album/recommend")
    Observable<BaseResponse<List<RecommendAlbumListData>>> getRecommendAlbumListData();

    /**
     * 推荐教程
     * http://www.wanandroid.com/course/recommend
     *
     * @return 教程列表
     */
    @GET("course/recommend")
    Observable<BaseResponse<List<RecommendCourseListData>>> getRecommendCourseListData();
}
