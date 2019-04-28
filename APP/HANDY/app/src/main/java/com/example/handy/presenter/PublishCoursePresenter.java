package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.LoginContract;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class PublishCoursePresenter extends BasePresenter<PublishCourseContract.View> implements PublishCourseContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public PublishCoursePresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

}
