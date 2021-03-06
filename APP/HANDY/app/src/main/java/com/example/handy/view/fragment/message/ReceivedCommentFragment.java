package com.example.handy.view.fragment.message;


import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.presenter.message.ReceivedCommentPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.FollowAdapter;
import com.example.handy.view.adapter.ReceiveCommentAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedCommentFragment extends BaseRootFragment<ReceivedCommentPagerPresenter>
        implements ReceivedCommentPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.receive_comment_recycler_view)
    RecyclerView myCommentRecyclerview;

    private List<CommentMessageData> myCommentMessageList;
    private ReceiveCommentAdapter mCommentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_received_comment;
    }

    public static ReceivedCommentFragment getInstance(String param1, String param2) {
        ReceivedCommentFragment fragment = new ReceivedCommentFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void showReceiveMessage(List<CommentMessageData> commentMessageData, boolean isRefresh){
        if (commentMessageData == null) {
            return;
        }
        if (isRefresh) {
            this.myCommentMessageList = commentMessageData;
            mCommentAdapter.replaceData(commentMessageData);
        } else {
            this.myCommentMessageList.addAll(commentMessageData);
            mCommentAdapter.addData(commentMessageData);
        }
        showNormal();
        // 点击跳转事件
        mCommentAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setRefresh();
        mPresenter.getReceiveMessage(true);
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
        myCommentMessageList = new ArrayList<>();
        mCommentAdapter = new ReceiveCommentAdapter(R.layout.item_receive_message, myCommentMessageList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        myCommentRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        myCommentRecyclerview.setHasFixedSize(true);
        myCommentRecyclerview.setAdapter(mCommentAdapter);
    }

    // 跳转
    private void startCourseDetailPager(View view, int position) {
        if (mCommentAdapter.getData().size() <= 0 || mCommentAdapter.getData().size() < position) {
            return;
        }

        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        //JudgeUtils.startCourseDetailActivity(_mActivity,
        //        options,
        //        mCommentAdapter.getData().get(position).getCourseId(),
        //        mCommentAdapter.getData().get(position).getCourseTitle()
        //);
        JudgeUtils.startCourseDetailActivityWithoutOption(_mActivity,
                mCommentAdapter.getData().get(position).getCourseId(),
                mCommentAdapter.getData().get(position).getCourseTitle()
        );
    }
}


