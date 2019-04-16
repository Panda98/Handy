package com.example.handy.base.presenter;

import com.example.handy.base.view.AbstractView;
import com.example.handy.core.DataManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base Presenter
 * 管理事件流订阅的生命周期
 *
 * @author wangziang
 * @date 2019/04/07
 */
public class BasePresenter<T extends AbstractView> implements AbstractPresenter<T> {

    protected T mView;
    private CompositeDisposable compositeDisposable;
    private DataManager mDataManager;

    public BasePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    @Override
    public void setLoginStatus(boolean loginStatus) {
        mDataManager.setLoginStatus(loginStatus);
    }

    @Override
    public boolean getLoginStatus() {
        return mDataManager.getLoginStatus();
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void setLoginAccount(int account) {
        mDataManager.setLoginAccount(account);
    }

    @Override
    public void setLoginPassword(String password) {
        mDataManager.setLoginPassword(password);
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public int getCurrentPage() {
        return mDataManager.getCurrentPage();
    }
}
