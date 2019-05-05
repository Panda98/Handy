package com.example.handy.presenter;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CommentDialogContract;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.DataManager;

import javax.inject.Inject;

public class CommentDialogPresenter extends BasePresenter<CommentDialogContract.View> implements CommentDialogContract.Presenter {

    @Inject
    public CommentDialogPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void comment(Boolean status) {

    }
}
