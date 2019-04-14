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
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.presenter.FollowPagerPresenter;
import com.example.handy.presenter.MainPagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowPagerFragment extends BaseRootFragment<FollowPagerPresenter>
        implements FollowPagerContract.View {


    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private boolean isRecreate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isRecreate = getArguments().getBoolean(Constants.ARG_PARAM1);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow_pager;
    }

    @Override
    protected void initView() {
        super.initView();
        //initRecyclerView();
    }

    public static FollowPagerFragment getInstance(String param1, String param2) {
        FollowPagerFragment fragment = new FollowPagerFragment();
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
}
