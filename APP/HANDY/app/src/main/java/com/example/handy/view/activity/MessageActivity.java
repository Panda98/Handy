package com.example.handy.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.MessageContract;
import com.example.handy.presenter.MessagePresenter;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.fragment.message.ReceivedCommentFragment;
import com.example.handy.view.fragment.message.ReceivedLikeFragment;
import com.example.handy.view.fragment.message.ReceivedReplyFragment;

import butterknife.BindView;

public class MessageActivity extends BaseActivity<MessagePresenter> implements MessageContract.View {

    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;

    @BindView(R.id.message_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.message_viewpager)
    ViewPager viewPager;

    private Fragment[] mFragmentArrays = new Fragment[3];

    private String[] mTabTitles = new String[3];

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);

        mTitleTv.setText("消息");
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initTabView();
    }

    private void initTabView() {

        mTabTitles[0] = "赞";
        mTabTitles[1] = "评论";
        mTabTitles[2] = "回复";
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = ReceivedLikeFragment.getInstance(null, null);;
        mFragmentArrays[1] = ReceivedCommentFragment.getInstance(null, null);;
        mFragmentArrays[2] = ReceivedReplyFragment.getInstance(null, null);;

        PagerAdapter pagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);
    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }

    @Override
    protected void initEventAndData() {

    }
}
