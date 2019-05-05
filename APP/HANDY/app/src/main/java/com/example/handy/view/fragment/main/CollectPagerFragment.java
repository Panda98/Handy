package com.example.handy.view.fragment.main;


import android.app.ActivityOptions;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.presenter.CollectPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.AlbumCoverDataAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectPagerFragment extends BaseRootFragment<CollectPagerPresenter>
        implements CollectPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.my_collect_course_recycler_view)
    RecyclerView mCourseRecyclerView;

    @BindView(R.id.my_collect_album_recycler_view)
    RecyclerView mAlbumRecyclerView;

    private AlbumCoverDataAdapter mAdapter;
    private List<AlbumListData> mCollectedAlbumList;


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

    @Override
    public void showMyCollectedCourseData(List<CourseData> courseDataList, boolean isRefresh){

    };

    @Override
    public void showMyCollectedAlbumData(List<AlbumCoverData> albumCoverDataList, boolean isRefresh){
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mCollectedAlbumList = albumCoverDataList;
            mAdapter.replaceData(albumCoverDataList);
        } else {
            this.mCollectedAlbumList.addAll(albumCoverDataList);
            mAdapter.addData(albumCoverDataList);
        }
        showNormal();
        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> startAlbumDetailPager(view, position));
    }

    private void startAlbumDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        JudgeUtils.startAlbumDetailActivity(this,
                options,
                mAdapter.getData().get(position).getAlbumId()
        );
    }




}
