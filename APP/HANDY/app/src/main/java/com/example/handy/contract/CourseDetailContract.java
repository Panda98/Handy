package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CourseDetailData;

public interface CourseDetailContract {

    interface View extends AbstractView {

        void showCourseDetail(CourseDetailData courseDetailData);

        void showFollowView();
        void showUnFollowView();

    }

    interface Presenter extends AbstractPresenter<CourseDetailContract.View> {

        // 获得教程详情
        void getCourseDetail(boolean isShowError, int courseId);
        // 关注
        void follow(int followId);
        // 取消关注
        void unFollow(int followId);

    }
}
