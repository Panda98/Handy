package com.example.handy.view.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.AuthorHomepageContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.presenter.AuthorHomepagePresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.HomepageAlbumAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthorHomepageActivity extends BaseActivity<AuthorHomepagePresenter> implements AuthorHomepageContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.homepage_user_image)
    NiceImageView mUserImage;
    @BindView(R.id.homepage_user_name)
    TextView mUserName;
    @BindView(R.id.homepage_follow_btn)
    TextView mFollowBtn;
    @BindView(R.id.homepage_user_follow)
    TextView mFollowNum;
    @BindView(R.id.homepage_user_follower)
    TextView mFanNum;
    @BindView(R.id.homepage_user_course_rv)
    RecyclerView mCourseRv;
    @BindView(R.id.homepage_course_more)
    TextView mCourseMoreBtn;
    @BindView(R.id.homepage_album_more)
    TextView mAlbumMoreBtn;
    @BindView(R.id.homepage_user_album_grid)
    GridView mAlbumGv;

    private int userId;
    private boolean followStatus;

    private List<CourseData> mCourseDataList;
    private RecommendCourseAdapter courseAdapter;
    private List<AlbumCoverData> mAlbumDataList;
    private HomepageAlbumAdapter albumAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_author_homepage;
    }

    @Override
    protected void initToolbar() {
        initBundleData();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText("Ta的主页");
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void initBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        this.userId = ((int) bundle.get(Constants.AUTHOR_ID));
        System.out.println(this.userId);
    }

    @OnClick({R.id.homepage_follow_btn, R.id.homepage_course_more, R.id.homepage_album_more})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.homepage_follow_btn:
                collectEvent();
                break;
            case R.id.homepage_course_more:
                startCourseMore(v);
                break;
            case R.id.homepage_album_more:
                startAlbumMore(v);
                break;
            default:
                break;
        }
    }

    // 关注
    private void collectEvent() {
        if (!mPresenter.getLoginStatus()) {
            CommonUtils.showMessage(this, getString(R.string.login_tint));
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if(this.followStatus){
            // 已关注
            mPresenter.unFollow(userId);
            unFollowBtnView();
            this.followStatus = false;
        }
        else {
            // 未关注
            mPresenter.follow(userId);
            followBtnView();
            this.followStatus = true;
        }
    }

    private void followBtnView() {
        this.mFollowBtn.setTextColor(getResources().getColor(R.color.normal_text));
        this.mFollowBtn.setBackground(getDrawable(R.drawable.bg_unfollow));
        this.mFollowBtn.setText(getString(R.string.unFollow));
    }

    private void unFollowBtnView() {
        this.mFollowBtn.setTextColor(getResources().getColor(R.color.follow_boarder));
        this.mFollowBtn.setBackground(getDrawable(R.drawable.bg_follow));
        this.mFollowBtn.setText(getString(R.string.follow));
    }

    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.loadAuthorHomepageData(this.userId);
        // 获得关注状态
        mPresenter.isFollow(this.userId);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(userId,false);
            refreshLayout.finishRefresh(1000);
        });
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mCourseDataList = new ArrayList<>();
        courseAdapter = new RecommendCourseAdapter(R.layout.item_recommend_course, mCourseDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mCourseRv.setLayoutManager(new LinearLayoutManager(this));
        mCourseRv.setHasFixedSize(true);
        mCourseRv.setAdapter(courseAdapter);
    }

    @Override
    public void showUserInfo(UserInfoData userInfoData) {
        // 设置用户头像
        if (!TextUtils.isEmpty(userInfoData.getUserPic())) {
            ImageLoader.loadToNIV(this, userInfoData.getUserPic(), mUserImage);
        }
        //设置用户名
        if(!TextUtils.isEmpty(userInfoData.getNickname())){
            mUserName.setText(userInfoData.getNickname());
        }
        //设置粉丝数
        if(!TextUtils.isEmpty(String.valueOf(userInfoData.getFanCount()))){
            mFanNum.setText(String.valueOf(userInfoData.getFanCount()));
        }
        //设置关注数
        if(!TextUtils.isEmpty(String.valueOf(userInfoData.getFollowCount()))){
            mFollowNum.setText(String.valueOf(userInfoData.getFollowCount()));
        }
    }

    @Override
    public void showUserPublishCourse(List<CourseData> courseData) {

        if (courseAdapter == null) {
            return;
        }

        this.mCourseDataList = courseData;
        courseAdapter.replaceData(courseData);

        // 点击跳转事件
        courseAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));


        showNormal();

    }

    @Override
    public void showUserPublishAlbum(List<AlbumCoverData> albumCoverData) {
        albumAdapter = new HomepageAlbumAdapter(this, R.layout.item_recommend_album, albumCoverData);
        mAlbumGv.setAdapter(albumAdapter);
        mAlbumGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startAlbumDetailPager(view,i);
            }
        });
    }

    @Override
    public void showFollowView() {
        CommonUtils.showMessage(this, getString(R.string.follow));
        System.out.println(getString(R.string.follow));
    }

    @Override
    public void showUnFollowView() {
        CommonUtils.showMessage(this, getString(R.string.unFollow));
        System.out.println(getString(R.string.unFollow));
    }

    @Override
    public void setFollowStatus(boolean isFollow) {
        if(isFollow){
            // 已关注
            followBtnView();
            this.followStatus = true;
        }
        else {
            // 未关注
            unFollowBtnView();
            this.followStatus = false;
        }
    }

    @Override
    public void setFollowVisibility(int userId) {

        if (this.userId == userId) {
            mFollowBtn.setVisibility(View.INVISIBLE);
        }

    }

    private void startCourseDetailPager(View view, int position) {
        if (courseAdapter.getData().size() <= 0 || courseAdapter.getData().size() < position) {
            return;
        }

        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        //JudgeUtils.startCourseDetailActivity(this,
        //        options,
        //        courseAdapter.getData().get(position).getCourseId(),
        //        courseAdapter.getData().get(position).getCourseTitle()
        //);
        JudgeUtils.startCourseDetailActivityWithoutOption(this,
                courseAdapter.getData().get(position).getCourseId(),
                courseAdapter.getData().get(position).getCourseTitle()
        );
    }

    private void startAlbumDetailPager(View view, int position) {
        if (albumAdapter.getData().size() <= 0 || albumAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        JudgeUtils.startAlbumDetailActivity(this,
                options,
                albumAdapter.getData().get(position).getAlbumId()
        );
    }

    private void startAlbumMore(View view) {

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        JudgeUtils.startMoreAlbumActivity(this,
                options,
                userId
        );
    }

    private void startCourseMore(View view) {

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, getString(R.string.share_view));
        JudgeUtils.startMoreCourseActivity(this,
                options,
                userId
        );
    }
}
