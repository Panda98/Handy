package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

/**
 * @author wangziang
 * @date 2019/04/11
 */

public interface MainContract {

    interface View extends AbstractView {

        /**
         * Show switch project
         */
        void showSwitchProject();

        /**
         * Show switch navigation
         */
        void showSwitchNavigation();

        /**
         * Show auto login view
         */
        void showAutoLoginView();

        /**
         * Show logout success
         */
        void showLogoutSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Set current page
         *
         * @param page current page
         */
        void setCurrentPage(int page);
    }
}
