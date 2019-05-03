package com.example.handy.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.AccountPagerContract;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.presenter.AccountPagerPresenter;
import com.example.handy.presenter.CollectPagerPresenter;
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
        setRefresh();
        mPresenter.getUserInfo(true);

        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
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
    }
}