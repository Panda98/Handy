package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.UserInfoData;

public interface AccountPagerContract {

    interface View extends AbstractView {

        void showUserInfo(UserInfoData userInfoData);
    }

    interface Presenter extends AbstractPresenter<AccountPagerContract.View> {
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

        /**
         * Load more data 显示更多数据
         */
        void loadMoreData();

    }
}
