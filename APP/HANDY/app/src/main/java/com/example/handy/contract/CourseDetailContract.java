package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

public interface CourseDetailContract {

    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<CourseDetailContract.View> {

    }
}
