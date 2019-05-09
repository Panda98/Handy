package com.example.handy.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.base.fragment.BaseFragment;
import com.example.handy.contract.MainContract;
import com.example.handy.presenter.MainPresenter;
import com.example.handy.utils.BottomNavigationViewHelper;
import com.example.handy.utils.CommonAlertDialog;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.fragment.main.AccountPagerFragment;
import com.example.handy.view.fragment.main.CollectPagerFragment;
import com.example.handy.view.fragment.main.FollowPagerFragment;
import com.example.handy.view.fragment.main.MainPagerFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.fragment_group)
    FrameLayout mFrameGroup;

    private ArrayList<BaseFragment> mFragments;

    private MainPagerFragment mMainPagerFragment;
    private FollowPagerFragment mFollowPagerFragment;
    private CollectPagerFragment mCollectPagerFragment;
    private AccountPagerFragment mAccountPagerFragment;

    private int mLastFgIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mTitleTv.setText(getString(R.string.navigation_home_pager));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragments = new ArrayList<>();
        if (savedInstanceState == null) {
            initPager(false, Constants.TYPE_MAIN_PAGER);
        } else {
            mBottomNavigationView.setSelectedItemId(R.id.tab_main_pager);
            initPager(true, Constants.TYPE_SETTING);
        }
    }

    private void initPager(boolean isRecreate, int position) {
        mMainPagerFragment = MainPagerFragment.getInstance(isRecreate, null);
        mFragments.add(mMainPagerFragment);
        initFragments();
        init();
        switchFragment(position);
    }

    private void init() {
        mPresenter.setCurrentPage(Constants.TYPE_MAIN_PAGER);
        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    //替换图标
                    item.setIcon(R.drawable.homepage_icon_selected);
                    loadPager(getString(R.string.navigation_home_pager), 0,
                            mMainPagerFragment, Constants.TYPE_MAIN_PAGER);
                    break;
                case R.id.tab_follow:
                    //替换图标
                    if (!mPresenter.getLoginStatus()) {
                        startActivity(new Intent(this, LoginActivity.class));
                        CommonUtils.showMessage(this, getString(R.string.login_tint));
                        break;
                    }
                    loadPager(getString(R.string.navigation_follow), 1,
                            mFollowPagerFragment, Constants.TYPE_FOLLOW_PAGER);
                    break;
                case R.id.tab_collect:
                    if (!mPresenter.getLoginStatus()) {
                        startActivity(new Intent(this, LoginActivity.class));
                        CommonUtils.showMessage(this, getString(R.string.login_tint));
                        break;
                    }
                    loadPager(getString(R.string.navigation_collect), 2,
                            mCollectPagerFragment, Constants.TYPE_COLLECT_PAGER);
                    break;
                case R.id.tab_account:
                    if (!mPresenter.getLoginStatus()) {
                        startActivity(new Intent(this, LoginActivity.class));
                        CommonUtils.showMessage(this, getString(R.string.login_tint));
                        break;
                    }
                    loadPager(getString(R.string.navigation_account), 3,
                            mAccountPagerFragment, Constants.TYPE_ACCOUNT_PAGER);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    private void loadPager(String title, int position, BaseFragment mFragment, int pagerType) {
        mTitleTv.setText(title);
        switchFragment(position);
        mFragment.reload();
        mPresenter.setCurrentPage(pagerType);
    }

    private void initFragments() {
        mFollowPagerFragment = FollowPagerFragment.getInstance(null, null);
        mCollectPagerFragment = CollectPagerFragment.getInstance(null, null);
        mAccountPagerFragment = AccountPagerFragment.getInstance(null, null);

        mFragments.add(mFollowPagerFragment);
        mFragments.add(mCollectPagerFragment);
        mFragments.add(mAccountPagerFragment);

    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= Constants.TYPE_COLLECT) {
            mBottomNavigationView.setVisibility(View.INVISIBLE);
        } else {
            mBottomNavigationView.setVisibility(View.VISIBLE);
        }
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void showSwitchProject() {

    }

    @Override
    public void showSwitchNavigation() {

    }

    @Override
    public void showAutoLoginView() {

    }

    @Override
    public void showLogoutSuccess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CommonAlertDialog.newInstance().cancelDialog(true);
    }
}
