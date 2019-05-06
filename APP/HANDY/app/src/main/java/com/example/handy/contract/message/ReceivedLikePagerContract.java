package com.example.handy.contract.message;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.LikeMessageData;

import java.util.List;

public interface ReceivedLikePagerContract {

    interface View extends AbstractView {
        void showLikeMessage(List<LikeMessageData> likeMessageData, boolean isRefresh);

    }

    interface Presenter extends AbstractPresenter<View> {
        void getLikeMessage(boolean isShowError);

        void autoRefresh(boolean isShowError);

        public void loadMore();

        public void loadMoreData();

    }
}
