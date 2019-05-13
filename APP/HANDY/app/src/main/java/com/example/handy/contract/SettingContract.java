package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

public interface SettingContract {

    interface View extends AbstractView {

        /**
         * Show logout success
         */
        void showLogoutSuccess();

    }

    interface Presenter extends AbstractPresenter<SettingContract.View> {

        /**
         * Get auto cache state
         *
         * @return if auto cache state
         */
        boolean getAutoCacheState();

        /**
         * Get no image state
         *
         * @return if has image state
         */
        boolean getNoImageState();

        /**
         * Set no image state
         *
         * @param b current no image state
         */
        void setNoImageState(boolean b);

        /**
         * Set auto cache state
         *
         * @param b current auto cache state
         */
        void setAutoCacheState(boolean b);

        /**
         * log out 退出登录
         *
         */
        void logout();
    }
}
