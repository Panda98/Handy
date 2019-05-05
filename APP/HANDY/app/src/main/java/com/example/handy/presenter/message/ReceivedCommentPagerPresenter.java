package com.example.handy.presenter.message;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class ReceivedCommentPagerPresenter extends BasePresenter<ReceivedCommentPagerContract.View> implements ReceivedCommentPagerContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ReceivedCommentPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }
}
