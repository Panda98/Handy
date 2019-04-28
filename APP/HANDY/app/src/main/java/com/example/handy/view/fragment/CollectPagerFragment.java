package com.example.handy.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.presenter.CollectPagerPresenter;
import com.example.handy.presenter.FollowPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.view.adapter.FollowAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectPagerFragment extends BaseRootFragment<CollectPagerPresenter>
        implements CollectPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect_pager;
    }


    public static CollectPagerFragment getInstance(String param1, String param2) {
        CollectPagerFragment fragment = new CollectPagerFragment();
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



}
