package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

/**
 * @author wangziang
 * @date 2019/04/09
 */

public interface RegisterContract {

    interface View extends AbstractView {

        /**
         * Show register data
         *
         */
        void showRegisterSuccess();
    }

    interface Presenter extends AbstractPresenter<View> {

        /**
         * 注册
         *
         * @param username user name
         * @param password password
         * @param rePassword re password
         */
        void getRegisterData(String username, String password, String rePassword);
    }
}
