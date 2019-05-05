package com.example.handy.contract.message;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

public interface ReceivedCommentPagerContract {

    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<ReceivedCommentPagerContract.View> {

    }
}
