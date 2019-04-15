package com.example.handy.core.http;

import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.vo.LoginView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpHelper {
    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param loginView user name password
     * @return 项目类别数据
     */
    Observable<BaseResponse<LoginData>> getLoginData(LoginView loginView);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param loginView user name password
     * @return 登陆数据
     */
    Observable<BaseResponse<LoginData>> getRegisterData(LoginView loginView);

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
    Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData();

    /**
     * 推荐教程
     * http://www.wanandroid.com/course/recommend
     *
     * @return 教程列表
     */
    Observable<BaseResponse<List<RecommendCourseData>>> getRecommendCourseListData(int currentPage, int n);
}
