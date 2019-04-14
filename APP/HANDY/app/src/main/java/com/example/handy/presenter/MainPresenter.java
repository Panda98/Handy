package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MainContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter{

    private DataManager mDataManager;

    @Inject
    MainPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }

    @Override
    public void setCurrentPage(int page) {
        mDataManager.setCurrentPage(page);
    }


}
