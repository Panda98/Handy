package com.example.handy.view.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.presenter.PublishCoursePresenter;
import com.example.handy.utils.StatusBarUtil;

import butterknife.BindView;


public class PublishCourseActivity  extends BaseActivity<PublishCoursePresenter> implements PublishCourseContract.View {


    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_course;
    }

    @Override
    protected void initToolbar() {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mTitleTv.setText(getString(R.string.publish_course_toolbar_title));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

    }

    @Override
    protected void initEventAndData() {

    }
}
