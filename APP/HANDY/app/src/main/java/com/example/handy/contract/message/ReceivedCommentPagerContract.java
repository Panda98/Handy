package com.example.handy.contract.message;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CommentMessageData;

import java.util.List;

public interface ReceivedCommentPagerContract {

    interface View extends AbstractView {
        void showReceiveMessage(List<CommentMessageData> commentMessageData, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<ReceivedCommentPagerContract.View> {
        void getReceiveMessage(boolean isShowError);

        void autoRefresh(boolean isShowError);

        public void loadMore();

        public void loadMoreData();

    }
}
