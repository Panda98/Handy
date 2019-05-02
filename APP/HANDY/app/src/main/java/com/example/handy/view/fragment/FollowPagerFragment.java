package com.example.handy.view.fragment;


import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.presenter.FollowPagerPresenter;
import com.example.handy.presenter.MainPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.FollowAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowPagerFragment extends BaseRootFragment<FollowPagerPresenter>
        implements FollowPagerContract.View {


    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.follow_pager_recycler_view)
    RecyclerView mRecyclerView;


    private List<FollowData> followDataList;
    private FollowAdapter mAdapter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow_pager;
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
        mPresenter.getFollowDataList(true);
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
        initRecyclerView();
    }

    private void initRecyclerView() {
        followDataList = new ArrayList<>();
        mAdapter = new FollowAdapter(R.layout.item_follow, followDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void showFollowData(List<FollowData> followDataList, boolean isRefresh) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_FOLLOW_PAGER) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.followDataList = followDataList;
            mAdapter.replaceData(followDataList);
        } else {
            this.followDataList.addAll(followDataList);
            mAdapter.addData(followDataList);
        }
        showNormal();
        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));
    }

    // 跳转
    private void startCourseDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        JudgeUtils.startCourseDetailActivity(_mActivity,
                options,
                mAdapter.getData().get(position).getCourseId()
        );
    }
}
