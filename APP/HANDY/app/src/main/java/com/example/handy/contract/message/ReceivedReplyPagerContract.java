package com.example.handy.contract.message;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.ReplyMessageData;

import java.util.List;

public interface ReceivedReplyPagerContract {

    interface View extends AbstractView {
        void showReplyMessage(List<ReplyMessageData> replyMessageData, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<View> {
        void getReplyMessage(boolean isShowError);

        void autoRefresh(boolean isShowError);

        public void loadMore();

        public void loadMoreData();

    }
}

