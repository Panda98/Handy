package com.example.handy.core.http.api;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumListData;
import com.example.handy.core.bean.RecommendCourseListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public interface Apis {
    String HOST = "https://www.wanandroid.com/";

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<LoginData>> getRegisterData(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

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
