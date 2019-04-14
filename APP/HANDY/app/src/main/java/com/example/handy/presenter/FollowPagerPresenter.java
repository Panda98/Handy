package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class FollowPagerPresenter extends BasePresenter<FollowPagerContract.View> implements FollowPagerContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public FollowPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(FollowPagerContract.View view) {
        super.attachView(view);
    }

    @Override
    public void autoRefresh(boolean isShowError) {

    }

    @Override
    public void loadMore() {

    }
}
