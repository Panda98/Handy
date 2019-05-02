package com.example.handy.view.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.ItemData;
import com.example.handy.core.bean.StepData;
import com.example.handy.presenter.CourseDetailPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.CourseStepAdapter;
import com.example.handy.view.adapter.FollowAdapter;
import com.shehuan.niv.NiceImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Function;

public class CourseDetailActivity extends BaseActivity<CourseDetailPresenter> implements CourseDetailContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

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

    ArrayAdapter<String> mItemArrayAdapter;
    CourseStepAdapter mCourseStepAdapter;

    private List<StepData> stepDataList;

    private int courseId;
    private String courseTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initToolbar() {
        initBundleData();
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

    @Override
    protected void initEventAndData() {
        mPresenter.getCourseDetail(true, this.courseId);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }

    }

    private void initBundleData() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        this.courseId = ((int) bundle.get(Constants.COURSE_ID));
        this.courseTitle = ((String) bundle.get(Constants.COURSE_Title));
        System.out.println(this.courseId);
        System.out.println(this.courseTitle);
    }

    // 收藏事件
    private void collectEvent() {
        if (!mPresenter.getLoginStatus()) {
            CommonUtils.showMessage(this, getString(R.string.login_tint));
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            //mPresenter.addCollectCourse(courseId);

        }
    }

    @Override
    public void showCourseDetail(CourseDetailData courseDetailData) {
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

    private void initStep() {
        mCourseStepAdapter = new CourseStepAdapter(R.layout.item_course_detail_step, this.stepDataList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mStepRv.setLayoutManager(new LinearLayoutManager(this));
        mStepRv.setHasFixedSize(true);
        mStepRv.setAdapter(mCourseStepAdapter);
    }

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
