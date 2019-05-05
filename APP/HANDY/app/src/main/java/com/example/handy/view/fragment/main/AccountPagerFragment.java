package com.example.handy.view.fragment.main;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.AccountPagerContract;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.presenter.AccountPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountPagerFragment extends BaseRootFragment<AccountPagerPresenter>
        implements AccountPagerContract.View{


    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.account_pager_user_image)
    NiceImageView mUserImage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_pager;
    }


    public static AccountPagerFragment getInstance(String param1, String param2) {
        AccountPagerFragment fragment = new AccountPagerFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mPresenter.getUserInfo(true);

        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void showUserInfo(UserInfoData userInfoData) {
        // 设置用户头像
        if (!TextUtils.isEmpty(userInfoData.getUserPic())) {
            ImageLoader.loadToNIV(_mActivity, userInfoData.getUserPic(), mUserImage);
        }

        showNormal();
    }
}