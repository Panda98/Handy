package com.example.handy.view.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.RegisterContract;
import com.example.handy.presenter.RegisterPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.StatusBarUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.register_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.register_account_edit)
    EditText mAccountEdit;
    @BindView(R.id.register_password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.register_confirm_password_edit)
    EditText mConfirmPasswordEdit;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            mAccountEdit.requestFocus();
            inputMethodManager.showSoftInput(mAccountEdit, 0);
        }
        mPresenter.addRxBindingSubscribe(RxView.clicks(mRegisterBtn)
                .throttleFirst(Constants.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> mPresenter != null)
                .subscribe(o -> register()));

    }

    @Override
    public void showRegisterSuccess() {
        CommonUtils.showSnackMessage(this, getString(R.string.register_success));
        onBackPressedSupport();
    }

    private void register() {
        mPresenter.getRegisterData(mAccountEdit.getText().toString().trim(),
                mPasswordEdit.getText().toString().trim(),
                mConfirmPasswordEdit.getText().toString().trim());
    }
}
