package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

public interface FollowPagerContract {
    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<View> {
        /**
         * Auto refresh 自动刷新
         *
         * @param isShowError If show error
         */
        void autoRefresh(boolean isShowError);

        /**
         * Load more 加载更多
         */
        void loadMore();
    }
}
