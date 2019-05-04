package com.example.handy.view.activity;

import android.app.ActivityOptions;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.MyPublishAlbumContract;
import com.example.handy.contract.MyPublishCourseContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.presenter.MyPublishAlbumPresenter;
import com.example.handy.presenter.MyPublishCoursePresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.AlbumCoverDataAdapter;
import com.example.handy.view.adapter.AlbumDetailAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPublishAlbumActivity extends BaseActivity<MyPublishAlbumPresenter> implements MyPublishAlbumContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.my_publish_album_recycler_view)
    RecyclerView mRecyclerView;

    private List<AlbumCoverData> mPublishAlbumList;
    private AlbumCoverDataAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_publish_album;
    }

    @Override
    protected void initToolbar() {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText("我发布的专辑");
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.getMyPublishAlbumDataList(true);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1000);
        });
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mPublishAlbumList = new ArrayList<>();
        mAdapter = new AlbumCoverDataAdapter(R.layout.item_album_abstract, mPublishAlbumList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMyPublishAlbumData(List<AlbumCoverData> albumListDataList, boolean isRefresh) {

        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mPublishAlbumList = albumListDataList;
            mAdapter.replaceData(albumListDataList);
        } else {
            this.mPublishAlbumList.addAll(albumListDataList);
            mAdapter.addData(albumListDataList);
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
