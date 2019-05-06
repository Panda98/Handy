package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CourseData;

import java.util.List;

public interface CourseListByLabelContract {

    interface View extends AbstractView {

        /**
         * Show my publish course data
         *
         * @param courseDataList List<CourseData>
         */
        void showCourseListByLabel(List<CourseData> courseDataList, boolean isRefresh);
    }

    interface Presenter extends AbstractPresenter<CourseListByLabelContract.View> {
        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(int albumId, boolean isShowError);

        /**
         * Load more 加载更多
         */
        void loadMore(int albumId);

        /**
         * Load more data 显示更多数据
         */
        void loadMoreData(int albumId);

        /**
         * Get follow data list 根据标签获得教程
         *
         * @param isShowError If show error
         */
        void getCourseListByLabel(int albumId, boolean isShowError);
    }
}
