package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.LabelData;
import com.example.handy.core.bean.PublishCourseData;

import java.io.File;
import java.util.List;

public interface PublishCourseContract {

    interface View extends AbstractView {
        void afterUploadPic(String url,int index);
        void afterPublish(String message);
        void showLabels(List<LabelData>labelDataList);
    }

    interface Presenter extends AbstractPresenter<View> {

        void publish(PublishCourseData data);
        void uploadPic(File file, int index);
        void requestLabels();

    }
}
