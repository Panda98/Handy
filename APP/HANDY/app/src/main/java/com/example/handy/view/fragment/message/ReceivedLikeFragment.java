package com.example.handy.view.fragment.message;


import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.contract.message.ReceivedLikePagerContract;
import com.example.handy.core.bean.LikeMessageData;
import com.example.handy.presenter.message.ReceiveLikePagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.ReceiveLikeAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedLikeFragment extends BaseRootFragment<ReceiveLikePagerPresenter>
        implements ReceivedLikePagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.receive_kike_recycler_view)
    RecyclerView myLikeRecyclerview;

    private List<LikeMessageData> mylikeMessageList;
    private ReceiveLikeAdapter mLikeAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_received_like;
    }

    public static ReceivedLikeFragment getInstance(String param1, String param2) {
        ReceivedLikeFragment fragment = new ReceivedLikeFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void showLikeMessage(List<LikeMessageData> likeMessageData, boolean isRefresh){
        if (likeMessageData == null) {
            return;
        }
        if (isRefresh) {
            this.mylikeMessageList = likeMessageData;
            mLikeAdapter.replaceData(likeMessageData);
        } else {
            this.mylikeMessageList.addAll(likeMessageData);
            mLikeAdapter.addData(likeMessageData);
        }
        showNormal();
        // 点击跳转事件
        mLikeAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setRefresh();
        mPresenter.getLikeMessage(true);
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
        mylikeMessageList = new ArrayList<>();
        mLikeAdapter = new ReceiveLikeAdapter(R.layout.item_receive_message, mylikeMessageList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        myLikeRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        myLikeRecyclerview.setHasFixedSize(true);
        myLikeRecyclerview.setAdapter(mLikeAdapter);
    }

    // 跳转
    private void startCourseDetailPager(View view, int position) {
        if (mLikeAdapter.getData().size() <= 0 || mLikeAdapter.getData().size() < position) {
            return;
        }

        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        //JudgeUtils.startCourseDetailActivity(_mActivity,
        //        options,
        //        mLikeAdapter.getData().get(position).getCourseId(),
        //        mLikeAdapter.getData().get(position).getCourseTitle()
        //);
        JudgeUtils.startCourseDetailActivityWithoutOption(_mActivity,
                mLikeAdapter.getData().get(position).getCourseId(),
                mLikeAdapter.getData().get(position).getCourseTitle()
        );
    }
}


