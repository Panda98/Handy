package com.example.handy.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.ItemData;
import com.example.handy.core.bean.StepData;
import com.example.handy.presenter.CourseDetailPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.CommentAdapter;
import com.example.handy.view.adapter.CourseStepAdapter;
import com.example.handy.view.fragment.CommentDialogFragment;
import com.example.handy.view.fragment.SelectAlbumFragment;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Function;

public class CourseDetailActivity extends BaseActivity<CourseDetailPresenter> implements CourseDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.course_detail_cover)
    NiceImageView mCourseCoverNIv;

    @BindView(R.id.course_detail_author_cover)
    NiceImageView mAuthorCoverNIv;

    @BindView(R.id.course_detail_author_user_name)
    TextView mAuthorNameTv;

    @BindView(R.id.course_detail_publish_time)
    TextView mPublishTimeTv;

    @BindView(R.id.course_detail_follow_btn)
    TextView mFollowBtn;

    @BindView(R.id.course_detail_abstract)
    TextView mCourseIntro;

    @BindView(R.id.course_detail_item)
    ListView mItemLv;

    @BindView(R.id.course_detail_step)
    RecyclerView mStepRv;

    @BindView(R.id.course_detail_comment)
    RecyclerView mCommentRv;

    @BindView(R.id.collect_button)
    LinearLayout collectBtn;

    @BindView(R.id.comment_btn)
    TextView commentBtn;

    @BindView(R.id.like_course_button)
    LikeButton likeButton;

    ArrayAdapter<String> mItemArrayAdapter;
    CourseStepAdapter mCourseStepAdapter;
    private CommentAdapter mCommentAdapter;

    private SelectAlbumFragment searchDialogFragment;
    private CommentDialogFragment commentDialogFragment;

    private List<CommentData> commentDataList;
    private List<StepData> stepDataList;

    private int courseId;
    private int followId;
    private boolean followStatus;
    private String courseTitle;
    private int userId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initToolbar() {
        initBundleData();
        initLikeButton();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText(this.courseTitle);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    private void initLikeButton() {
        //likeButton.setLiked(true);
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                System.out.println("liked");
                mPresenter.likeCourse(courseId);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                System.out.println("unliked");
                mPresenter.unlikeCourse(courseId);
            }
        });
    }
    @Override
    protected void initEventAndData() {
        setRefresh();
        mPresenter.getCourseDetail(true, this.courseId);
        mPresenter.getCommentList(true,this.courseId);
        mPresenter.getLikeStatus(this.courseId);
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
        initCommentRecyclerView();
    }



    private void initBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        this.courseId = ((int) bundle.get(Constants.COURSE_ID));
        this.courseTitle = ((String) bundle.get(Constants.COURSE_Title));
        System.out.println(this.courseId);
        System.out.println(this.courseTitle);
    }

    @OnClick({R.id.course_detail_follow_btn, R.id.collect_button, R.id.comment_btn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.course_detail_follow_btn:
                collectEvent();
                break;
            case R.id.collect_button:
                if (searchDialogFragment == null) {
                    searchDialogFragment = new SelectAlbumFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("courseId", this.courseId);
                    searchDialogFragment.setArguments(bundle);
                }
                if (!isDestroyed() && searchDialogFragment.isAdded()) {
                    searchDialogFragment.dismiss();
                }
                searchDialogFragment.show(getSupportFragmentManager(), "SelectAlbumFragment");
                break;
            case R.id.comment_btn:
                if (commentDialogFragment == null) {
                    commentDialogFragment = new CommentDialogFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("courseId", this.courseId);
                    commentDialogFragment.setArguments(bundle);
                }
                if (!isDestroyed() && commentDialogFragment.isAdded()) {
                    commentDialogFragment.dismiss();
                }
                commentDialogFragment.show(getSupportFragmentManager(), "CommentDialogFragment");
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
            mPresenter.unFollow(followId);
            unFollowBtnView();
            this.followStatus = false;
        }
        else {
            // 未关注
            mPresenter.follow(followId);
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
    public void showCourseDetail(CourseDetailData courseDetailData) {

        this.userId = courseDetailData.getUserId();

        // 设置教程封面
        if (!TextUtils.isEmpty(courseDetailData.getCourseCover())) {
            ImageLoader.loadToNIV(this, courseDetailData.getCourseCover(), mCourseCoverNIv);
        }

        // 设置作者头像
        if (!TextUtils.isEmpty(courseDetailData.getUserPic())) {
            ImageLoader.loadToNIV(this, courseDetailData.getUserPic(), mAuthorCoverNIv);
        }

        // 设置作者昵称
        if (!TextUtils.isEmpty(courseDetailData.getNickName())) {
            mAuthorNameTv.setText(courseDetailData.getNickName());
        }

        // 设置发布时间
        if (!TextUtils.isEmpty(courseDetailData.getUpdateTime())) {
            mPublishTimeTv.setText(courseDetailData.getUpdateTime());
        }

        // 设置简介
        if (!TextUtils.isEmpty(courseDetailData.getCourseIntro())) {
            mCourseIntro.setText(courseDetailData.getCourseIntro());
        }

        this.followId = courseDetailData.getUserId();
        // 获得关注状态
        mPresenter.isFollow(this.followId);

        this.stepDataList = courseDetailData.getStepList();

        String[] itemNameList = new String[courseDetailData.getItemList().size()];
        for (ItemData i : courseDetailData.getItemList()) {
            itemNameList[i.getItemTag()-1] = i.getItemName();
        }
        System.out.println(Arrays.toString(itemNameList));

        mItemArrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item_course_detail_material,
                R.id.course_detail_item_name,
                itemNameList
        );
        mItemLv.setAdapter(mItemArrayAdapter);
        setListViewHeightBasedOnChildren(mItemLv);

        initStep();

    }

    private void initCommentRecyclerView() {
        commentDataList = new ArrayList<>();
        mCommentAdapter = new CommentAdapter(R.layout.item_comment, commentDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mCommentRv.setLayoutManager(new LinearLayoutManager(this));
        mCommentRv.setHasFixedSize(true);
        mCommentRv.setAdapter(mCommentAdapter);
    }

    @Override
    public void showCommentData(List<CommentData> commentDataList, boolean isRefresh) {

        if (mCommentAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.commentDataList = commentDataList;
            mCommentAdapter.replaceData(commentDataList);
        } else {
            this.commentDataList.addAll(commentDataList);
            mCommentAdapter.addData(commentDataList);
        }
        showNormal();
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
    public void setLikeStatus(boolean isLike) {
        likeButton.setLiked(isLike);
        //likeButton.setLiked(true);

    }

    @Override
    public void setFollowVisibility(int userId) {
        if (this.userId == userId) {
            mFollowBtn.setVisibility(View.INVISIBLE);
        }
    }

    private void initStep() {
        mCourseStepAdapter = new CourseStepAdapter(R.layout.item_course_detail_step, this.stepDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mStepRv.setLayoutManager(new LinearLayoutManager(this));
        mStepRv.setHasFixedSize(true);
        mStepRv.setAdapter(mCourseStepAdapter);
    }

    //ListView 高度
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            // listItem.measure(0, 0);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
