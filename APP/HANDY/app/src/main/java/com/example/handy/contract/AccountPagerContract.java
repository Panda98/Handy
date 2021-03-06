package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.UserInfoData;

import java.util.List;

public interface AccountPagerContract {

    interface View extends AbstractView {

        void showUserInfo(UserInfoData userInfoData);

        void showUserPublishData(List<CourseData> courseData);

        void showUserPublishAlbum(List<AlbumCoverData> albumCoverData);

    }

    interface Presenter extends AbstractPresenter<AccountPagerContract.View> {

        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);

        /**
         * Get User Info 加载用户数据
         *
         * @param isShowError If show error
         */
        void getUserInfo(boolean isShowError);

        /**
         * Get My Course 加载我的教程信息
         *
         * @param isShowError If show error
         */
        void getMyCourse(boolean isShowError);

        /**
         * Get My Album 加载我的专辑信息
         */
        void getMyAlbum(boolean isShowError);


    }
}
