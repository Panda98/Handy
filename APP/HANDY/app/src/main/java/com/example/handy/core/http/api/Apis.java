package com.example.handy.core.http.api;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.vo.LoginView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public interface Apis {
    String HOST = "http://106.13.106.249:8080/";

    /**
     * 登陆
     *
     * @param loginData name password
     * @return 登陆数据
     */
    @POST("user/login")
    Observable<BaseResponse<LoginData>> getLoginData(@Body LoginView loginData);

    /**
     * 注册
     *
     * @param loginData user name password
     * @return 注册数据
     */
    @POST("user/regist")
    Observable<BaseResponse<LoginData>> getRegisterData(@Body LoginView loginData);

    /**
     * 退出登录
     *
     * @return 登陆数据
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 热门推荐
     *
     * @return 轮播数据
     */
    @GET("main/banner")
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 推荐专辑
     *
     * @return 专辑列表
     */
    @GET("album/recommend")
    Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData(@Query("uid")int uid);

    /**
     * 推荐教程
     *
     * @return 教程列表
     */
    @GET("course/recommend")
    Observable<BaseResponse<List<RecommendCourseData>>> getRecommendCourseListData(@Query("uid")int uid, @Query("page_no") int currentPage,@Query("n")int n);
}
