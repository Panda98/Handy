package com.example.handy.view.activity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
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
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AlbumDetailActivity extends BaseActivity<AlbumDetailPresenter> implements AlbumDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.album_delete_btn)
    TextView deleteAlbumBtn;

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.album_detail_course_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.album_detail_cover)
    NiceImageView mAlbumCover;
    @BindView(R.id.album_detail_title)
    TextView mAlbumTitle;
    @BindView(R.id.album_detail_author_name)
    TextView mAlbumAuthor;
    @BindView(R.id.collect_album_button)
    LikeButton collectButton;

    private AlbumCoverData albumCoverData;
    private List<CourseData> courseDataList;

    private RecommendCourseAdapter mAdapter;

    private int albumId;
    //private boolean followStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initToolbar() {
        initBundleData();
        initRecyclerView();
        initCollectButton();

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

    private void initCollectButton() {
        //likeButton.setLiked(true);
        collectButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                System.out.println("收藏");
                mPresenter.collectAlbum(albumId);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                System.out.println("取消收藏");
                mPresenter.unCollectAlbum(albumId);
            }
        });
    }

    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.getAlbumCoverData(true, albumId);
        mPresenter.getAlbumDetailData(true, albumId);
        mPresenter.isCollectAlbum(this.albumId);
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
        mAdapter = new RecommendCourseAdapter(R.layout.item_recommend_course, courseDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.album_delete_btn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.album_delete_btn:
                AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);

                //监听下方button点击事件
                alertDialogBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.deleteAlbum(albumId);
                        CommonUtils.showSnackMessage(AlbumDetailActivity.this, "删除成功");
                        onBackPressedSupport();
                    }
                });

                //监听下方button点击事件
                alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                alertDialogBuilder.setMessage("确认要删除此专辑吗？");//设置标题
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;
            default:
                break;
        }
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

        //设置作者
        if (!TextUtils.isEmpty(albumCoverData.getAuthorName())) {
            mAlbumAuthor.setText(albumCoverData.getAuthorName());
        }
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

    @Override
    public void showCollectResult() {

    }

    @Override
    public void showUnCollectResult() {

    }

    @Override
    public void setCollectStatus(boolean status) {
        collectButton.setLiked(status);
    }

    @Override
    public void showMyAlbumView() {
        collectButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showOthersAlbumView() {
        deleteAlbumBtn.setVisibility(View.INVISIBLE);
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
