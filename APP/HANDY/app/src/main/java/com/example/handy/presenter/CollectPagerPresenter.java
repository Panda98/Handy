package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class CollectPagerPresenter extends BasePresenter<CollectPagerContract.View> implements CollectPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public CollectPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectPagerContract.View view) {
        super.attachView(view);
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
    }

    @Override
    public void loadMoreData() {

    }

}
