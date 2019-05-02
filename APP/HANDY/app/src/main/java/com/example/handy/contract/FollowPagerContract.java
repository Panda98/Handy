package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.FollowData;

import java.util.List;

public interface FollowPagerContract {

    interface View extends AbstractView {
        /**
         * Show follow data 关注人的信息
         *
         * @param followDataList List<FollowData>
         */
        void showFollowData(List<FollowData> followDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<View> {
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
         * Get follow data list 获得关注信息列表
         *
         * @param isShowError If show error
         */
        void getFollowDataList(boolean isShowError);
    }
}
