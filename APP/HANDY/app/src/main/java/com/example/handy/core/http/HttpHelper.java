package com.example.handy.core.http;

import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.vo.LoginView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface HttpHelper {
    /**
     * 登陆
     *
     * @param loginView user name password
     * @return 项目类别数据
     */
    Observable<BaseResponse<LoginData>> getLoginData(LoginView loginView);

    /**
     * 注册
     *
     * @param loginView user name password
     * @return 登陆数据
     */
    Observable<BaseResponse<LoginData>> getRegisterData(LoginView loginView);

    /**
     * 退出登录
     */
    @GET("user/logout/json")
    Observable<BaseResponse<LoginData>> logout();

    /**
     * 热门推荐
     *
     * @return 轮播图数据
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 推荐专辑
     *
     * @return 专辑列表
     */
    Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData(int uid);

    /**
     * 推荐教程
     *
     * @return 教程列表
     */
    Observable<BaseResponse<List<RecommendCourseData>>> getRecommendCourseListData(int uid, int currentPage, int n);

    /**
     * 推荐教程
     *
     * @return 教程列表
     */
    Observable<BaseResponse<List<FollowData>>> getFollowDataList(int uid, int currentPage, int n);

    /**
     * 获得教程详情
     *
     * @return 教程详情信息
     */
    Observable<BaseResponse<CourseDetailData>> getCourseDetail(int courseId);

    /**
     * 关注
     *
     * @return 结果
     */
    Observable<BaseResponse<Boolean>> follow(int uid, int followId);

    /**
     * 取消关注
     *
     * @return 结果
     */
    Observable<BaseResponse<Boolean>> unFollow(int uid, int followId);

    /**
     * 获得用户专辑列表
     *
     * @return AlbumListData
     */
    Observable<BaseResponse<List<AlbumListData>>> getUserAlbumList(int userId);

    /**
     * 获得收藏专辑列表
     *
     * @return AlbumListData
     */
    Observable<BaseResponse<List<AlbumListData>>> getCollectAlbumList(int userId);

    /**
     * 收藏
     *
     * @return 结果
     */
    Observable<BaseResponse> collect(int courseId, int albumId);

    /**
     * 取消收藏
     *
     * @return 结果
     */
    Observable<BaseResponse> unCollect(int courseId, int albumId);

    /**
     * 获取收藏状态
     *
     * @return 结果
     */
    Observable<BaseResponse> isCollect(int userId, int courseId);

    /**
     * 点赞
     *
     * @return null
     */
    Observable<BaseResponse> like(int userId, int courseId);

    /**
     * 取消点赞
     *
     * @return null
     */
    Observable<BaseResponse> unLike(int userId, int courseId);

    /**
     * 获取点赞状态
     *
     * @return 结果
     */
    Observable<BaseResponse> isLike(int userId, int courseId);

    /**
     * 获取自己发布的教程
     *
     * @return 结果
     */
    Observable<BaseResponse<List<RecommendCourseData>>> getUserPublishCourse(int userId, int currentPage, int n);


    /**
     * 上传教程
     *
     * @return 教程列表
     */
    Observable<BaseResponse> uploadCourse(Map<String, RequestBody> partMap, MultipartBody.Part... files);
}
