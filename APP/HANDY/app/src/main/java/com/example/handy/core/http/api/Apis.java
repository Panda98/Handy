package com.example.handy.core.http.api;

import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.CommentReplyData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LabelData;
import com.example.handy.core.bean.LikeMessageData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.ReplyMessageData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.core.vo.CreateAlbumView;
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
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public interface Apis {
    String HOST = "http://106.13.106.249:8080/";
    //String HOST = "http://192.168.2.28:8080/Handy/";
    //String HOST = "http://192.168.2.7:8080/";

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
     * 获得用户信息
     *
     * @return 用户信息
     */
    @GET("user")
    Observable<BaseResponse<UserInfoData>> getUserInfo(@Query("uid")int uid);

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
    Observable<BaseResponse<List<CourseData>>> getRecommendCourseListData(@Query("uid")int uid, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 关注列表
     *
     * @return 关注列表
     */
    @GET("follow/moments")
    Observable<BaseResponse<List<FollowData>>> getFollowDataList(@Query("uid")int uid, @Query("page_no") int currentPage, @Query("n")int n);


    /**
     * 获得教程详情
     *
     * @return 教程详情信息
     */
    @GET("course/detail")
    Observable<BaseResponse<CourseDetailData>> getCourseDetail(@Query("courseId")int courseId);

    /**
     * 关注
     *
     * @return 结果
     */
    @GET("follow/follow")
    Observable<BaseResponse<Boolean>> follow(@Query("uid")int uid, @Query("follow_id")int followId);

    /**
     * 取消关注
     *
     * @return 结果
     */
    @GET("follow/unfollow")
    Observable<BaseResponse<Boolean>> unFollow(@Query("uid")int uid, @Query("follow_id")int followId);

    /**
     * 获取关注状态
     *
     * @return 结果
     */
    @GET("follow/hasFollowed")
    Observable<BaseResponse<Boolean>> isFollow(@Query("uid")int uid, @Query("follow_id")int followId);

    /**
     * 获得用户私有专辑列表
     *
     * @return AlbumListData
     */
    @GET("album/my_private_list")
    Observable<BaseResponse<List<AlbumCoverData>>> getUserPrivateAlbumList(@Query("uid")int userId);

    /**
     * 获得用户分享的专辑列表
     *
     * @return AlbumListData
     */
    @GET("album/my_shared_list")
    Observable<BaseResponse<List<AlbumCoverData>>> getUserSharedAlbumList(@Query("uid")int userId);

    /**
     * 获得收藏专辑列表
     *
     * @return AlbumListData
     */
    @GET("album/collection")
    Observable<BaseResponse<List<AlbumCoverData>>> getCollectAlbumList(@Query("uid")int userId);

    /**
     * 收藏专辑
     *
     * @return null
     */
    @GET("album/collect")
    Observable<BaseResponse> collectAlbum(@Query("uid")int userId, @Query("albumid") int albumId);

    /**
     * 取消收藏专辑
     *
     * @return null
     */
    @GET("album/uncollect")
    Observable<BaseResponse> unCollectAlbum(@Query("uid")int userId, @Query("albumid") int albumId);

    /**
     * 获得收藏专辑状态
     *
     * @return null
     */
    @GET("album/iscollect")
    Observable<BaseResponse<Boolean>> isCollectAlbum(@Query("uid")int userId, @Query("albumid") int albumId);

    /**
     * 创建专辑
     *
     * @return null
     */
    @POST("album/create")
    Observable<BaseResponse> createAlbum(@Body CreateAlbumView createAlbumView);

    /**
     * 删除专辑
     *
     * @return null
     */
    @GET("album/delete")
    Observable<BaseResponse> deleteAlbum(@Query("albumid") int albumId);

    /**
     * 收藏教程
     *
     * @return 结果
     */
    @GET("course/collect")
    Observable<BaseResponse> collectCourse(@Query("courseId")int courseId, @Query("albumId")int albumId);

    /**
     * 取消收藏教程
     *
     * @return 结果
     */
    @GET("course/uncollect")
    Observable<BaseResponse> unCollectCourse(@Query("courseId")int courseId, @Query("albumId")int albumId);

    /**
     * 获取教程收藏状态
     *
     * @return 结果
     */
    @GET("course/iscollected")
    Observable<BaseResponse> isCollectCourse(@Query("userId")int userId, @Query("courseId")int courseId);

    /**
     * 点赞
     *
     * @return null
     */
    @GET("course/like")
    Observable<BaseResponse> like(@Query("userId")int userId, @Query("courseId")int courseId);

    /**
     * 取消点赞
     *
     * @return null
     */
    @GET("course/unlike")
    Observable<BaseResponse> unLike(@Query("userId")int userId, @Query("courseId")int courseId);

    /**
     * 获取点赞状态
     *
     * @return 结果
     */
    @GET("course/isliked")
    Observable<BaseResponse<Boolean>> isLike(@Query("userId")int userId, @Query("courseId")int courseId);

    /**
     * 获取自己发布的教程
     *
     * @return 结果
     */
    @GET("course")
    Observable<BaseResponse<List<CourseData>>> getUserPublishCourse(@Query("uid")int userId, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 获取标签
     *
     * @return 标签列表
     */
    @GET("course/label")
    Observable<BaseResponse<List<LabelData>>> getLabelList();

    /**
     * 发布教程
     *
     * @return 上传结果
     */
    @POST("course/publish")
    Observable<BaseResponse> uploadCourse(@Body PublishCourseData publishCourseData);

    /**
     * 根据标签获得教程
     *
     * @return 结果
     */
    @GET("course/labeledCourse")
    Observable<BaseResponse<List<CourseData>>> getCourseWithLabel(@Query("labelId")int labelId, @Query("page_no") int currentPage, @Query("n")int n);


    /**
     * 上传图片
     *
     * @return url
     */
    @Multipart
    @POST("uploadImg")
    Observable<BaseResponse<String>> uploadImage(@Part MultipartBody.Part file);


    /**
     * 上传评论
     *
     * @param commentView PostCommentView
     * @return 状态
     */
    @POST("comment/push")
    Observable<BaseResponse<Boolean>> postComment(@Body PostCommentView commentView);

    /**
     * 回复评论
     *
     * @param replyCommentView ReplyCommentView
     * @return 状态
     */
    @POST("comment/push")
    Observable<BaseResponse<Boolean>> postCommentReply(@Body ReplyCommentView replyCommentView);

    /**
     * 获取教程评论
     *
     * @return 评论信息
     */
    @GET("comment/all")
    Observable<BaseResponse<List<CommentData>>> getComment(@Query("course_id")int course_id, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 获取评论回复
     *
     * @return 回复信息
     */
    @GET("comment/all")
    Observable<BaseResponse<CommentReplyData>> getCommentReply(@Query("comment_id")int course_id, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 获取专辑中的教程信息
     *
     * @return 教程信息
     */
    @GET("album/detail")
    Observable<BaseResponse<List<CourseData>>> getAlbumCourseData(@Query("uid")int uid, @Query("albumid") int albumId, @Query("page")int currentPage, @Query("n")int n);

    /**
     * 获取专辑封面信息
     *
     * @return 回复信息
     */
    @GET("album/brief")
    Observable<BaseResponse<AlbumCoverData>> getAlbumCoverData(@Query("albumid") int albumId);

    /**
     * 消息——收到的评论
     *
     * @return 回复信息
     */
    @GET("message/courseComment")
    Observable<BaseResponse<List<CommentMessageData>>> getCommentMessage(@Query("uid")int uid, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 消息——收到的回复
     *
     * @return 回复信息
     */
    @GET("message/commentReply")
    Observable<BaseResponse<List<ReplyMessageData>>> getReplyMessage(@Query("uid")int uid, @Query("page_no") int currentPage, @Query("n")int n);

    /**
     * 消息——收到的赞
     *
     * @return 回复信息
     */
    @GET("message/courseLike")
    Observable<BaseResponse<List<LikeMessageData>>> getLikeMessage(@Query("uid")int uid, @Query("page_no") int currentPage, @Query("n")int n);


}
