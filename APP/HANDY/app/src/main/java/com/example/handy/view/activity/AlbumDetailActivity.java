package com.example.handy.view.activity;

import android.app.ActivityOptions;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.AlbumDetailContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.presenter.AlbumDetailPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.AlbumDetailAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AlbumDetailActivity extends BaseActivity<AlbumDetailPresenter> implements AlbumDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.album_detail_course_rv)
    RecyclerView mRecyclerView;

    // header
    LinearLayout mHeaderGroup;
    LinearLayout mHeader;
    NiceImageView mAlbumCover;
    TextView mAlbumTitle;
    TextView mAlbumAuthor;
    TextView mAlbumCollectNum;

    private AlbumCoverData albumCoverData;
    private List<CourseData> courseDataList;

    private AlbumDetailAdapter mAdapter;

    private int albumId;
    private boolean followStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }



    @Override
    protected void initToolbar() {
        initBundleData();
        initRecyclerView();

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText("专辑详情");
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        this.albumId = ((int) bundle.get(Constants.ALBUM_ID));
        System.out.println(this.albumId);
    }

    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.getAlbumCoverData(true, albumId);
        mPresenter.getAlbumDetailData(true, albumId);
        //mPresenter.getCommentList(true,this.courseId);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }

    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false, albumId);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    private void initRecyclerView() {
        courseDataList = new ArrayList<>();
        mAdapter = new AlbumDetailAdapter(R.layout.item_recommend_course, courseDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        // header
        mHeaderGroup = ((LinearLayout) LayoutInflater.from(this).inflate(R.layout.album_detail_header, null));
        initHeader();
        mHeaderGroup.removeView(mHeader);
        mAdapter.addHeaderView(mHeader);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initHeader() {
        mHeader = mHeaderGroup.findViewById(R.id.album_detail_header);
        mAlbumCover = mHeaderGroup.findViewById(R.id.album_detail_cover);
        mAlbumTitle = mHeaderGroup.findViewById(R.id.album_detail_title);
        mAlbumAuthor = mHeaderGroup.findViewById(R.id.album_detail_author_name);
        mAlbumCollectNum = mHeaderGroup.findViewById(R.id.album_detail_collect_num);
    }

    @Override
    public void showAlbumCoverData(AlbumCoverData albumCoverData) {
        // 设置封面
        if (!TextUtils.isEmpty(albumCoverData.getAlbumPic())) {
            ImageLoader.loadToNIV(this, albumCoverData.getAlbumPic(), mAlbumCover);
        }

        // 设置专辑名
        if (!TextUtils.isEmpty(albumCoverData.getAlbumName())) {
            mAlbumTitle.setText(albumCoverData.getAlbumName());
        }

        // 设置作者
        //if (!TextUtils.isEmpty(albumCoverData.getAuthorId())) {
        //    mAlbumTitle.setText(albumCoverData.getAlbumName());
        //}
    }

    @Override
    public void showAlbumCourseData(List<CourseData> courseDataList, boolean isRefresh) {

        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.courseDataList = courseDataList;
            mAdapter.replaceData(courseDataList);
        } else {
            this.courseDataList.addAll(courseDataList);
            mAdapter.addData(courseDataList);
        }

        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));

        showNormal();
    }

    private void startCourseDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        JudgeUtils.startCourseDetailActivity(this,
                options,
                mAdapter.getData().get(position).getCourseId(),
                mAdapter.getData().get(position).getCourseTitle()
        );
    }
}
