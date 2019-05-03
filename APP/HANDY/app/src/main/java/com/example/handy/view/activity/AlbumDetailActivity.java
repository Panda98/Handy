package com.example.handy.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.AlbumDetailContract;
import com.example.handy.contract.LoginContract;
import com.example.handy.core.bean.AlbumDetailData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.presenter.AlbumDetailPresenter;
import com.example.handy.presenter.LoginPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class AlbumDetailActivity extends BaseActivity<AlbumDetailPresenter> implements AlbumDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    private int albumId;
    private boolean followStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initToolbar() {
        initBundleData();
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
        //mPresenter.getCourseDetail(true, this.courseId);
        //mPresenter.getCommentList(true,this.courseId);
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
    public void showAlbumCoverData(AlbumDetailData albumDetailData) {

    }

    @Override
    public void showAlbumCourseData(List<RecommendCourseData> courseDataList, boolean isRefresh) {

    }
}
