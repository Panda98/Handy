package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CourseDetailData;

public interface CourseDetailContract {

    interface View extends AbstractView {

        void showCourseDetail(CourseDetailData courseDetailData);

    }

    interface Presenter extends AbstractPresenter<CourseDetailContract.View> {

        void getCourseDetail(boolean isShowError, int courseId);

    }
}
