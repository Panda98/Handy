package com.example.handy.core.http;

import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CommentReplyData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.core.vo.LoginView;
import com.example.handy.core.vo.PostCommentView;
import com.example.handy.core.vo.ReplyCommentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
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
     * 获得用户信息
     *
     * @return 用户信息
     */
    Observable<BaseResponse<UserInfoData>> getUserInfo(int uid);

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
    Observable<BaseResponse<List<CourseData>>> getRecommendCourseListData(int uid, int currentPage, int n);

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
     * 获取关注状态
     *
     * @return 结果
     */
    Observable<BaseResponse<Boolean>> isFollow(int uid, int followId);

    /**
     * 获得用户私有专辑列表
     *
     * @return AlbumListData
     */
    Observable<BaseResponse<List<AlbumCoverData>>> getUserPrivateAlbumList(int userId);

    /**
     * 获得用户分享的专辑列表
     *
     * @return AlbumListData
     */
    Observable<BaseResponse<List<AlbumCoverData>>> getUserSharedAlbumList(int userId);

    /**
     * 获得收藏专辑列表
     *
     * @return AlbumListData
     */
    Observable<BaseResponse<List<AlbumCoverData>>> getCollectAlbumList(int userId);

    /**
     * 收藏教程
     *
     * @return 结果
     */
    Observable<BaseResponse> collectCourse(int courseId, int albumId);

    /**
     * 取消收藏教程
     *
     * @return 结果
     */
    Observable<BaseResponse> unCollectCourse(int courseId, int albumId);

    /**
     * 获取教程收藏状态
     *
     * @return 结果
     */
    Observable<BaseResponse> isCollectCourse(int userId, int courseId);

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
    Observable<BaseResponse<Boolean>> isLike(int userId, int courseId);

    /**
     * 获取自己发布的教程
     *
     * @return 结果
     */
    Observable<BaseResponse<List<CourseData>>> getUserPublishCourse(int userId, int currentPage, int n);

    /**
     * 发布教程
     *
     * @return 上传结果
     */
    Observable<BaseResponse> uploadCourse(PublishCourseData publishCourseData);

    /**
     * 上传图片
     *
     * @return url
     */
    @Multipart
    Observable<BaseResponse<String>> uploadImage(byte[] data);


    /**
     * 上传评论
     *
     * @param commentView PostCommentView
     * @return 状态
     */
    Observable<BaseResponse<Boolean>> postComment(PostCommentView commentView);

    /**
     * 回复评论
     *
     * @param replyCommentView ReplyCommentView
     * @return 状态
     */
    Observable<BaseResponse<Boolean>> postCommentReply(ReplyCommentView replyCommentView);

    /**
     * 获取教程评论
     *
     * @return 评论信息
     */
    Observable<BaseResponse<List<CommentData>>> getComment(int course_id, int currentPage, int n);

    /**
     * 获取评论回复
     *
     * @return 回复信息
     */
    Observable<BaseResponse<CommentReplyData>> getCommentReply(int course_id, int currentPage, int n);

    /**
     * 获取专辑中的教程信息
     *
     * @return 教程信息
     */
    Observable<BaseResponse<List<CourseData>>> getAlbumCourseData(int uid, int albumId, int currentPage, int n);

    /**
     * 获取专辑封面信息
     *
     * @return 回复信息
     */
    Observable<BaseResponse<AlbumCoverData>> getAlbumCoverData(int albumId);

}
