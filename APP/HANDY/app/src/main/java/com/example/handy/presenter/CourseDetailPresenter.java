package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class CourseDetailPresenter extends BasePresenter<CourseDetailContract.View> implements CourseDetailContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public CourseDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }
}
