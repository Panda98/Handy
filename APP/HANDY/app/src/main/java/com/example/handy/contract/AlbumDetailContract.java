package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.RecommendCourseData;

import java.util.List;

public interface AlbumDetailContract {

    interface View extends AbstractView {
        /**
         * Show album data 显示专辑信息
         *
         * @param albumDetailData AlbumDetailData
         */
        void showAlbumCoverData(AlbumDetailData albumDetailData);

        /**
         * Show album data 显示专辑内的教程信息
         *
         * @param courseDataList List<RecommendCourseData>
         */
        void showAlbumCourseData(List<RecommendCourseData> courseDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<AlbumDetailContract.View> {

        /**
         * Get album detail data 获得专辑信息
         *
         * @param isShowError If show error
         */
        void getAlbumCoverData(boolean isShowError);

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
         * Load more data 显示更多数据
         */
        void loadMoreData();

        /**
         * Get album detail data 获得专辑信息
         *
         * @param isShowError If show error
         */
        void getAlbumDetailData(boolean isShowError);

        /**
         * Collect Album 收藏专辑
         */
        //void collectAlbum();

    }
}
