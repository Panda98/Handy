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
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.contract.message.ReceivedReplyPagerContract;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.ReplyMessageData;
import com.example.handy.presenter.message.ReceivedCommentPagerPresenter;
import com.example.handy.presenter.message.ReceivedReplyPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.ReceiveCommentAdapter;
import com.example.handy.view.adapter.ReceiveReplyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedReplyFragment extends BaseRootFragment<ReceivedReplyPagerPresenter>
        implements ReceivedReplyPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.receive_reply_recycler_view)
    RecyclerView myReplyRecyclerview;

    private List<ReplyMessageData> myReplyMessageList;
    private ReceiveReplyAdapter mReplyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_received_reply;
    }

    public static ReceivedReplyFragment getInstance(String param1, String param2) {
        ReceivedReplyFragment fragment = new ReceivedReplyFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void showReplyMessage(List<ReplyMessageData> replyMessageData, boolean isRefresh) {
        if (replyMessageData == null) {
            return;
        }
        if (isRefresh) {
            this.myReplyMessageList = replyMessageData;
            mReplyAdapter.replaceData(replyMessageData);
        } else {
            this.myReplyMessageList.addAll(replyMessageData);
            mReplyAdapter.addData(replyMessageData);
        }
        showNormal();
        // 点击跳转事件
        mReplyAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setRefresh();
        mPresenter.getReplyMessage(true);
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
        myReplyMessageList = new ArrayList<>();
        mReplyAdapter = new ReceiveReplyAdapter(R.layout.item_receive_reply, myReplyMessageList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        myReplyRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        myReplyRecyclerview.setHasFixedSize(true);
        myReplyRecyclerview.setAdapter(mReplyAdapter);
    }

    private void startCourseDetailPager(View view, int position) {
        if (mReplyAdapter.getData().size() <= 0 || mReplyAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        JudgeUtils.startCourseDetailActivity(_mActivity,
                options,
                mReplyAdapter.getData().get(position).getInCourseId(),
                mReplyAdapter.getData().get(position).getInCourseTitle()
        );
    }
}
