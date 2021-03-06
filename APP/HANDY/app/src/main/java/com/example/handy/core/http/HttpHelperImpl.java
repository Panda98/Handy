package com.example.handy.core.http;

import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.CommentReplyData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.LabelData;
import com.example.handy.core.bean.LikeMessageData;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.ReplyMessageData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.core.http.api.Apis;
import com.example.handy.core.vo.CreateAlbumView;
import com.example.handy.core.vo.LoginView;
import com.example.handy.core.vo.PostCommentView;
import com.example.handy.core.vo.ReplyCommentView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Query;

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
    public Observable<BaseResponse<LoginData>> getLoginData(LoginView loginView) {
        return mApis.getLoginData(loginView);
    }

    @Override
    public Observable<BaseResponse<UserInfoData>> getRegisterData(LoginView loginView) {
        return mApis.getRegisterData(loginView);
    }

    @Override
    public Observable<BaseResponse<LoginData>> logout() {
        return mApis.logout();
    }

    @Override
    public Observable<BaseResponse<UserInfoData>> getUserInfo(int uid) {
        return mApis.getUserInfo(uid);
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApis.getBannerData();
    }

    @Override
    public Observable<BaseResponse<List<RecommendAlbumData>>> getRecommendAlbumListData(int uid) {
        return mApis.getRecommendAlbumListData(uid);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getRecommendCourseListData(int uid, int currentPage, int n) {
        return mApis.getRecommendCourseListData(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<FollowData>>> getFollowDataList(int uid, int currentPage, int n) {
        return mApis.getFollowDataList(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<CourseDetailData>> getCourseDetail(int courseId, int userId) {
        return mApis.getCourseDetail(courseId, userId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> follow(int uid, int followId) {
        return mApis.follow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> unFollow(int uid, int followId) {
        return mApis.unFollow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> isFollow(int uid, int followId) {
        return mApis.isFollow(uid, followId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getUserPrivateAlbumList(int userId) {
        return mApis.getUserPrivateAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getUserSharedAlbumList(int userId) {
        return mApis.getUserSharedAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse<List<AlbumCoverData>>> getCollectAlbumList(int userId) {
        return mApis.getCollectAlbumList(userId);
    }

    @Override
    public Observable<BaseResponse> collectAlbum(int userId, int albumId) {
        return mApis.collectAlbum(userId, albumId);
    }

    @Override
    public Observable<BaseResponse> unCollectAlbum(int userId, int albumId) {
        return mApis.unCollectAlbum(userId, albumId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> isCollectAlbum(int userId, int albumId) {
        return mApis.isCollectAlbum(userId, albumId);
    }

    @Override
    public Observable<BaseResponse> createAlbum(CreateAlbumView createAlbumView) {
        return mApis.createAlbum(createAlbumView);
    }

    @Override
    public Observable<BaseResponse> deleteAlbum(int albumId) {
        return mApis.deleteAlbum(albumId);
    }

    @Override
    public Observable<BaseResponse> collectCourse(int userId, int courseId, int albumId) {
        return mApis.collectCourse(userId, courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> unCollectCourse(int userId, int courseId, int albumId) {
        return mApis.unCollectCourse(userId, courseId, albumId);
    }

    @Override
    public Observable<BaseResponse> isCollectCourse(int userId, int courseId) {
        return mApis.isCollectCourse(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> like(int userId, int courseId) {
        return mApis.like(userId, courseId);
    }

    @Override
    public Observable<BaseResponse> unLike(int userId, int courseId) {
        return mApis.unLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse<Boolean>> isLike(int userId, int courseId) {
        return mApis.isLike(userId, courseId);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getUserPublishCourse(int userId, int currentPage, int n) {
        return mApis.getUserPublishCourse(userId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<LabelData>>> getLabelList() {
        return mApis.getLabelList();
    }

    @Override
    public Observable<BaseResponse> uploadCourse(PublishCourseData publishCourseData) {
        return mApis.uploadCourse(publishCourseData);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getCourseWithLabel(int labelId, int currentPage, int n) {
        return mApis.getCourseWithLabel(labelId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<String>> uploadImage(MultipartBody.Part file) {
        return mApis.uploadImage(file);
    }


    @Override
    public Observable<BaseResponse<Boolean>> postComment(PostCommentView commentView) {
        return mApis.postComment(commentView);
    }

    @Override
    public Observable<BaseResponse<Boolean>> postCommentReply(ReplyCommentView replyCommentView) {
        return mApis.postCommentReply(replyCommentView);
    }

    @Override
    public Observable<BaseResponse<List<CommentData>>> getComment(int course_id, int currentPage, int n) {
        return mApis.getComment(course_id, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<CommentReplyData>> getCommentReply(int course_id, int currentPage, int n) {
        return mApis.getCommentReply(course_id, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<CourseData>>> getAlbumCourseData(int uid, int albumId, int currentPage, int n) {
        return mApis.getAlbumCourseData(uid, albumId, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<AlbumCoverData>> getAlbumCoverData(int albumId) {
        return mApis.getAlbumCoverData(albumId);
    }

    @Override
    public Observable<BaseResponse<List<CommentMessageData>>> getCommentMessage(int uid, int currentPage, int n) {
        return mApis.getCommentMessage(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<ReplyMessageData>>> getReplyMessage(int uid, int currentPage, int n) {
        return mApis.getReplyMessage(uid, currentPage, n);
    }

    @Override
    public Observable<BaseResponse<List<LikeMessageData>>> getLikeMessage(int uid, int currentPage, int n) {
        return mApis.getLikeMessage(uid, currentPage, n);
    }
}
