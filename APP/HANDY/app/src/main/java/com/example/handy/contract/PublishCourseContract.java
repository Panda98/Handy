package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.PublishCourseData;

public interface PublishCourseContract {

    interface View extends AbstractView {
        void afterUploadPic(boolean res);
    }

    interface Presenter extends AbstractPresenter<View> {

        void publish(PublishCourseData data);
        void uploadPic(byte[] imgArr);

    }
}
