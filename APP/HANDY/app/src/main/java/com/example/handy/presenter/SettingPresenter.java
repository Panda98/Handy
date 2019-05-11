package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.component.RxBus;
import com.example.handy.contract.SettingContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.event.LoginEvent;
import com.example.handy.core.http.cookies.CookiesManager;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import javax.inject.Inject;

public class SettingPresenter extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    SettingPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public boolean getAutoCacheState() {
        return mDataManager.getAutoCacheState();
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean b) {
        mDataManager.setNoImageState(b);
    }

    @Override
    public void setAutoCacheState(boolean b) {
        mDataManager.setAutoCacheState(b);
    }

    @Override
    public void logout() {
        addSubscribe(mDataManager.logout()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleLogoutResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        HandyAPP.getInstance().getString(R.string.logout_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount(0);
                        setLoginStatus(false);
                        CookiesManager.clearAllCookies();
                        RxBus.getDefault().post(new LoginEvent(false));
                        mView.showLogoutSuccess();
                    }
                }));

    }
}
