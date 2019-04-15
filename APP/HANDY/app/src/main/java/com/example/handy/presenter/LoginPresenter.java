package com.example.handy.presenter;

import android.text.TextUtils;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.component.RxBus;
import com.example.handy.contract.LoginContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.event.LoginEvent;
import com.example.handy.core.vo.LoginView;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import javax.inject.Inject;


/**
 * @author wangziang
 * @date 2019/04/07
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getLoginData(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            mView.showSnackBar(HandyAPP.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        LoginView loginView = new LoginView(username,password);
        addSubscribe(mDataManager.getLoginData(loginView)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        HandyAPP.getInstance().getString(R.string.login_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        setLoginAccount(loginData.getId());
                        setLoginStatus(true);
                        RxBus.getDefault().post(new LoginEvent(true));
                        mView.showLoginSuccess();
                    }
                }));
    }
}
