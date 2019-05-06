package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.UserInfoData;

import java.util.List;

public interface AuthorHomepageContract {

    interface View extends AbstractView {

        /**
         * 显示用户信息
         *
         * @param userInfoData UserInfoData
         */
        void showUserInfo(UserInfoData userInfoData);

        /**
         * 显示用户发布的教程
         *
         * @param courseData List<CourseData>
         */
        void showUserPublishCourse(List<CourseData> courseData);

        /**
         * 显示用户发布的专辑
         *
         * @param albumCoverData List<AlbumCoverData>
         */
        void showUserPublishAlbum(List<AlbumCoverData> albumCoverData);

        /**
         * 显示已关注状态
         *
         */
        void showFollowView();

        /**
         * 显示未已关注状态
         *
         */
        void showUnFollowView();

        /**
         * 设置初始关注状态
         *
         * @param isFollow boolean
         */
        void setFollowStatus(boolean isFollow);

        /**
         * 设置是否显示关注按钮
         *
         * @param userId int
         */
        void setFollowVisibility(int userId);
    }

    interface Presenter extends AbstractPresenter<AuthorHomepageContract.View> {

        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(int userId, boolean isShowError);

        void loadAuthorHomepageData(int userId);

        /**
         * Get User Info 加载用户数据
         *
         * @param isShowError If show error
         */
        void getUserInfo(int userId, boolean isShowError);

        /**
         * Get Author Course 加载用户教程信息
         *
         * @param isShowError If show error
         */
        void getAuthorCourse(int userId, boolean isShowError);

        /**
         * Get Author Album 加载用户专辑信息
         */
        void getAuthorAlbum(int userId, boolean isShowError);

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

    }
}
