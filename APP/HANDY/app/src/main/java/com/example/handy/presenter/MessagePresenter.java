package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MainContract;
import com.example.handy.contract.MessageContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {

    private DataManager mDataManager;

    @Inject
    MessagePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

}
