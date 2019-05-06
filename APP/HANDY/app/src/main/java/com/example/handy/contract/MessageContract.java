package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CommentMessageData;

import java.util.List;

public interface MessageContract {

    interface View extends AbstractView {


    }

    interface Presenter extends AbstractPresenter<MessageContract.View> {


    }
}
