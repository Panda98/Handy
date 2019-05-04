package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;

import java.util.List;

public interface AlbumDetailContract {

    interface View extends AbstractView {
        /**
         * Show album data 显示专辑信息
         *
         * @param albumCoverData AlbumCoverData
         */
        void showAlbumCoverData(AlbumCoverData albumCoverData);

        /**
         * Show album data 显示专辑内的教程信息
         *
         * @param courseDataList List<CourseData>
         */
        void showAlbumCourseData(List<CourseData> courseDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<AlbumDetailContract.View> {

        /**
         * Get album detail data 获得专辑信息
         *
         * @param isShowError If show error
         */
        void getAlbumCoverData(boolean isShowError, int albumId);

        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError, int albumId);

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
        void getAlbumDetailData(boolean isShowError, int albumId);

        /**
         * Collect Album 收藏专辑
         */
        //void collectAlbum();

    }
}
