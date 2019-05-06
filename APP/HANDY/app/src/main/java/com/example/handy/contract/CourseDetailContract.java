package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CourseDetailData;

import java.util.List;

public interface CourseDetailContract {

    interface View extends AbstractView {

        void showCourseDetail(CourseDetailData courseDetailData);
        void showCommentData(List<CommentData> commentData, boolean isRefresh);

        void showFollowView();
        void showUnFollowView();
        void setFollowStatus(boolean isFollow);
        void setLikeStatus(boolean isLike);
        void setFollowVisibility(int userId);

    }

    interface Presenter extends AbstractPresenter<CourseDetailContract.View> {

        /**
         * 获得教程详情
         *
         * @param isShowError boolean
         * @param courseId int
         */
        void getCourseDetail(boolean isShowError, int courseId);
        /**
         * 关注
         *
         * @param followId int
         */
        void follow(int followId);
        /**
         * 取消关注
         *
         * @param followId int
         */
        void unFollow(int followId);
        /**
         * 关注状态
         *
         * @param followId int
         */
        void isFollow(int followId);

        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);

        /**
         * Load more 加载更多
         */
        void loadMore();

        /**
         * Load more data 显示更多评论
         */
        void loadMoreData();

        /**
         * Get follow data list 获得评论列表
         *
         * @param isShowError If show error
         */
        void getCommentList(boolean isShowError, int courseId);


        /**
         * like 点赞教程
         *
         */
        void likeCourse(int courseId);

        /**
         * unlike 取消点赞教程
         *un
         */
        void unlikeCourse(int courseId);

        /**
         * get like status
         *
         */
        void getLikeStatus(int courseId);

    }
}
