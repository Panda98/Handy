package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

/**
 * @author wangziang
 * @date 2019/04/07
 */

public interface LoginContract {

    interface View extends AbstractView {

        /**
         * Show login data
         *
         */
        void showLoginSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * Get Login data
         *
         * @param username user name
         * @param password password
         */
        void getLoginData(String username, String password);
    }
}
