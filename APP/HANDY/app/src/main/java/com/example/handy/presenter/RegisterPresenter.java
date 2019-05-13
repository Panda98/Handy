package com.example.handy.presenter;

import android.text.TextUtils;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.component.RxBus;
import com.example.handy.contract.LoginContract;
import com.example.handy.contract.RegisterContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.LoginData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.core.event.LoginEvent;
import com.example.handy.core.vo.LoginView;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import javax.inject.Inject;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getRegisterData(String username, String password, String rePassword) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            mView.showSnackBar(HandyAPP.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        if (!password.equals(rePassword)) {
            mView.showSnackBar(HandyAPP.getInstance().getString(R.string.password_not_same));
            return;
        }
        LoginView registerView = new LoginView(username,password);
        addSubscribe(mDataManager.getRegisterData(registerView)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<UserInfoData>(mView,
                        HandyAPP.getInstance().getString(R.string.register_fail)) {
                    @Override
                    public void onNext(UserInfoData loginData) {
                        mView.showRegisterSuccess();
                    }
                }));
    }
}