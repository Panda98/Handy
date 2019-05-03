package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.AlbumDetailContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class AlbumDetailPresenter extends BasePresenter<AlbumDetailContract.View> implements AlbumDetailContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public AlbumDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getAlbumCoverData(boolean isShowError) {

    }

    @Override
    public void autoRefresh(boolean isShowError) {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void getAlbumDetailData(boolean isShowError) {

    }
}
