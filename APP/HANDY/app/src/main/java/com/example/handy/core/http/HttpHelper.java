package com.example.handy.core.http;

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
     * 上传教程
     *
     * @return 教程列表
     */
    Observable<BaseResponse> uploadCourse(Map<String, RequestBody> partMap, MultipartBody.Part... files);
}
