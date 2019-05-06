package com.example.handy.view.fragment.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.presenter.message.ReceivedCommentPagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedReplyFragment extends BaseRootFragment<ReceivedCommentPagerPresenter>
        implements ReceivedCommentPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

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
    public void showReceiveMessage(List<CommentMessageData> commentMessageData, boolean isRefresh) {

    }
}
