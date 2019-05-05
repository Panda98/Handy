package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;

import java.util.List;

public interface SelectAlbumContract {

    interface View extends AbstractView {
        /**
         * Show my publish album data 显示我的专辑
         *
         * @param albumCoverDataList List<AlbumCoverData>
         */
        void showMyAlbumList(List<AlbumCoverData> albumCoverDataList, boolean isRefresh);

        /**
         * show collect 显示收藏结果
         *
         */
        void showCollectResult();

    }

    interface Presenter extends AbstractPresenter<SelectAlbumContract.View> {

        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);


        /**
         * Get my publish album list 获得我发布的专辑信息
         *
         * @param isShowError If show error
         */
        void getMyAlbumDataList(boolean isShowError);

        /**
         *  收藏教程
         *
         * @param courseId int
         * @param albumId int
         */
        void collectCourse(int courseId, int albumId);
    }
}
