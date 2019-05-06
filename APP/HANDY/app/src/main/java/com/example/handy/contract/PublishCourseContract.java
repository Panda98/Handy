package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.PublishCourseData;

public interface PublishCourseContract {

    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<View> {

        void getPublishCourseInfos(PublishCourseData data);

    }
}
