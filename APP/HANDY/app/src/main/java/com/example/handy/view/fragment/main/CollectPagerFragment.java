package com.example.handy.view.fragment.main;


import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.presenter.CollectPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.AlbumCoverDataAdapter;
import com.example.handy.view.fragment.dialog.CommentDialogFragment;
import com.example.handy.view.fragment.dialog.CreateAlbumDialogFragment;
import com.example.handy.view.fragment.dialog.SelectAlbumFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.haha.perflib.Main;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectPagerFragment extends BaseRootFragment<CollectPagerPresenter>
        implements CollectPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.my_album_recycler_view)
    RecyclerView myAlbumRecyclerView;

    @BindView(R.id.my_collect_album_recycler_view)
    RecyclerView mAlbumRecyclerView;

    @BindView(R.id.create_album_btn)
    ImageView mCreateAlbumBtn;

    private AlbumCoverDataAdapter collectAlbumAdapter;
    private AlbumCoverDataAdapter myAlbumAdapter;
    private List<AlbumCoverData> mCollectedAlbumList;
    private List<AlbumCoverData> myAlbumList;

    private CreateAlbumDialogFragment createAlbumDialogFragment;


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
        setRefresh();
        mPresenter.getCollectedAlbumDataList(true);
        mPresenter.getMyAlbumDataList(true);
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

    @OnClick({R.id.create_album_btn})
    void onClick(View v) {
        switch (v.getId()) {

            case R.id.create_album_btn:
                if (createAlbumDialogFragment == null) {
                    createAlbumDialogFragment = new CreateAlbumDialogFragment();
                }
                if (!isDetached() && createAlbumDialogFragment.isAdded()) {
                    createAlbumDialogFragment.dismiss();
                }
                createAlbumDialogFragment.show(getFragmentManager(), "CreateAlbumDialogFragment");
                break;

            default:
                break;
        }
    }

    @Override
    public void showMyAlbumData(List<AlbumCoverData> myAlbumDataList, boolean isRefresh) {
        if (myAlbumAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mCollectedAlbumList = myAlbumDataList;
            myAlbumAdapter.replaceData(myAlbumDataList);
        } else {
            this.mCollectedAlbumList.addAll(myAlbumDataList);
            myAlbumAdapter.addData(myAlbumDataList);
        }
        showNormal();
        // 点击跳转事件
        myAlbumAdapter.setOnItemClickListener((adapter, view, position) -> startAlbumDetailPager(view, position, myAlbumAdapter));
    }

    @Override
    public void showMyCollectedAlbumData(List<AlbumCoverData> albumCoverDataList, boolean isRefresh){
        if (collectAlbumAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mCollectedAlbumList = albumCoverDataList;
            collectAlbumAdapter.replaceData(albumCoverDataList);
        } else {
            this.mCollectedAlbumList.addAll(albumCoverDataList);
            collectAlbumAdapter.addData(albumCoverDataList);
        }
        showNormal();
        // 点击跳转事件
        collectAlbumAdapter.setOnItemClickListener((adapter, view, position) -> startAlbumDetailPager(view, position,collectAlbumAdapter));
    }

    private void startAlbumDetailPager(View view, int position, AlbumCoverDataAdapter adapter) {
        if (adapter.getData().size() <= 0 || adapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        JudgeUtils.startAlbumDetailActivity(_mActivity,
                options,
                adapter.getData().get(position).getAlbumId()
        );
    }

    private void initRecyclerView() {
        mCollectedAlbumList = new ArrayList<>();
        collectAlbumAdapter = new AlbumCoverDataAdapter(R.layout.item_album_abstract, mCollectedAlbumList);
        //collectAlbumAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAlbumRecyclerView.setHasFixedSize(true);
        mAlbumRecyclerView.setAdapter(collectAlbumAdapter);

        myAlbumList = new ArrayList<>();
        myAlbumAdapter = new AlbumCoverDataAdapter(R.layout.item_album_abstract, myAlbumList);
        //collectAlbumAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        myAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        myAlbumRecyclerView.setHasFixedSize(true);
        myAlbumRecyclerView.setAdapter(myAlbumAdapter);
    }






}
