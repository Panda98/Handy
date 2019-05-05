package com.example.handy.view.fragment.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.presenter.message.ReceivedCommentPagerPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedCommentFragment extends BaseRootFragment<ReceivedCommentPagerPresenter>
        implements ReceivedCommentPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

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

}
