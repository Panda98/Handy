package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.FollowData;

import java.util.List;

public interface CollectPagerContract {

    interface View extends AbstractView {

        void showMyAlbumData(List<AlbumCoverData> myAlbumDataList, boolean isRefresh);

        void showMyCollectedAlbumData(List<AlbumCoverData> albumListDataList, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<CollectPagerContract.View> {
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
         * 加载我的专辑
         */
        void getMyAlbumDataList(boolean isShowError);

        /**
         * 加载专辑信息
         */
        void getCollectedAlbumDataList(boolean isShowError);

    }
}
