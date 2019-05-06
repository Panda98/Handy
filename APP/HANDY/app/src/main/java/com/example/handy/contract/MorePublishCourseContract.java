package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CourseData;

import java.util.List;

public interface MorePublishCourseContract {

    interface View extends AbstractView {
        /**
         * Show my publish course data 我发布的教程
         *
         * @param courseDataList List<CourseData>
         */
        void showMyPublishCourseData(List<CourseData> courseDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<MorePublishCourseContract.View> {
        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(int userId, boolean isShowError);

        /**
         * Load more 加载更多
         */
        void loadMore();

        /**
         * Load more data 显示更多数据
         */
        void loadMoreData();

        /**
         * Get follow data list 获得我发布的教程
         *
         * @param isShowError If show error
         */
        void getPublishCourseDataList(int userId, boolean isShowError);
    }
}
