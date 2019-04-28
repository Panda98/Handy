package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;

import java.util.List;

public interface MainPagerContract {

    interface View extends AbstractView {

        /**
         * Show auto login success
         */
        void showAutoLoginSuccess();

        /**
         * Show auto login fail
         */
        void showAutoLoginFail();

        /**
         * Show banner data 热门推荐轮播栏信息
         *
         * @param bannerDataList List<BannerData>
         */
        void showBannerData(List<BannerData> bannerDataList);

        /**
         * Show content 显示推荐专辑
         *
         * @param recommendAlbumData RecommendAlbumData
         */
        void showRecommendAlbumList(List<RecommendAlbumData> recommendAlbumData);

        /**
         * Show content 显示推荐教程
         *
         * @param recommendCourseData RecommendCourseData
         */
        void showRecommendCourseList(List<RecommendCourseData> recommendCourseData, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<View> {


        /**
         * Load main pager data 加载主页数据
         */
        void loadMainPagerData();

        /**
         * Get recommend album list 获得推荐专辑列表
         *
         * @param isShowError If show error
         */
        void getRecommendAlbumList(boolean isShowError);

        /**
         * Get recommend course list 获得推荐教程列表
         *
         * @param isShowError If show error
         */
        void getRecommendCourseList(boolean isShowError);

        /**
         * Load more data 显示更多数据
         */
        void loadMoreData();

        /**
         * Get banner data 获取轮播图数据
         *
         * @param isShowError If show error
         */
        void getBannerData(boolean isShowError);

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

    }
}
