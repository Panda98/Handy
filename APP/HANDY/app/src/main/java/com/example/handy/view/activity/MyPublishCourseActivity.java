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
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.contract.MyPublishCourseContract;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.presenter.CourseDetailPresenter;
import com.example.handy.presenter.MyPublishCoursePresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.FollowAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPublishCourseActivity extends BaseActivity<MyPublishCoursePresenter> implements MyPublishCourseContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.my_publish_courser_recycler_view)
    RecyclerView mRecyclerView;

    private List<CourseData> mPublishCourseList;
    private RecommendCourseAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_publish_course;
    }

    @Override
    protected void initToolbar() {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText("我发布的教程");
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void initEventAndData() {

        setRefresh();
        mPresenter.getPublishCourseDataList(true);
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
    protected void onViewCreated() {
        super.onViewCreated();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mPublishCourseList = new ArrayList<>();
        mAdapter = new RecommendCourseAdapter(R.layout.item_recommend_course, mPublishCourseList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showMyPublishCourseData(List<CourseData> courseDataList, boolean isRefresh) {

        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mPublishCourseList = courseDataList;
            mAdapter.replaceData(courseDataList);
        } else {
            this.mPublishCourseList.addAll(courseDataList);
            mAdapter.addData(courseDataList);
        }
        showNormal();
        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));
    }

    // 跳转
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
