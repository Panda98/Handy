package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;

import java.util.List;

public interface MyPublishAlbumContract {

    interface View extends AbstractView {
        /**
         * Show my publish album data 我发布的专辑
         *
         * @param courseDataList List<AlbumCoverData>
         */
        void showMyPublishAlbumData(List<AlbumCoverData> courseDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<MyPublishAlbumContract.View> {
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
        void getMyPublishAlbumDataList(boolean isShowError);
    }
}
