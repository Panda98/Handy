package com.example.handy.view.fragment.main;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.AccountPagerContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.presenter.AccountPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.ImageLoader;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.activity.MessageActivity;
import com.example.handy.view.activity.MyPublishAlbumActivity;
import com.example.handy.view.activity.MyPublishCourseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shehuan.niv.NiceImageView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountPagerFragment extends BaseRootFragment<AccountPagerPresenter>
        implements AccountPagerContract.View{


    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.account_pager_user_image)
    NiceImageView mUserImage;

    @BindView(R.id.account_pager_user_name)
    TextView mUserName;

    @BindView(R.id.account_pager_user_follower)
    TextView mFollower;

    @BindView(R.id.account_pager_user_follow)
    TextView mFollow;

    @BindView(R.id.account_page_course1_img)
    NiceImageView mCourse1Image;

    @BindView(R.id.account_page_course1_title)
    TextView mCourse1Title;

    @BindView(R.id.account_page_course1_intro)
    TextView mCourse1intro;

    @BindView(R.id.account_page_course2_img)
    NiceImageView mCourse2Image;

    @BindView(R.id.account_page_course2_title)
    TextView mCourse2Title;

    @BindView(R.id.account_page_course2_intro)
    TextView mCourse2intro;

    @BindView(R.id.account_page_album1_img)
    NiceImageView mAlbum1Image;

    @BindView(R.id.account_page_album2_img)
    NiceImageView mAlbum2Image;

    @BindView(R.id.account_page_album3_img)
    NiceImageView mAlbum3Image;

    @BindView(R.id.account_page_album3_title)
    TextView mAlbum3Title;

    @BindView(R.id.account_page_album2_title)
    TextView mAlbum2Title;

    @BindView(R.id.account_page_album1_title)
    TextView mAlbum1Title;

    @BindView(R.id.account_page_course1)
    LinearLayout mCourse1;

    @BindView(R.id.account_page_course2)
    LinearLayout mCourse2;

    @BindView(R.id.account_album_more)
    TextView mAlbumMore;

    @BindView(R.id.account_course_more)
    TextView mCourseMore;

    @BindView(R.id.account_page_message_btn)
    LinearLayout mMessageBtn;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_pager;
    }


    public static AccountPagerFragment getInstance(String param1, String param2) {
        AccountPagerFragment fragment = new AccountPagerFragment();
        Bundle args = new Bundle();
        args.putString(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setListener();
        mPresenter.getUserInfo(true);
        mPresenter.getMyCourse(true);
        mPresenter.getMyAlbum(true);

        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }


    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    public void showUserInfo(UserInfoData userInfoData) {

        // 设置用户头像
        if (!TextUtils.isEmpty(userInfoData.getUserPic())) {
            ImageLoader.loadToNIV(_mActivity, userInfoData.getUserPic(), mUserImage);
        }
        //设置用户名
        if(!TextUtils.isEmpty(userInfoData.getNickname())){
            mUserName.setText(userInfoData.getNickname());
        }
        //设置粉丝数
        if(!TextUtils.isEmpty(String.valueOf(userInfoData.getFanCount()))){
            mFollower.setText(String.valueOf(userInfoData.getFanCount()));
        }
        //设置关注数
        if(!TextUtils.isEmpty(String.valueOf(userInfoData.getFollowCount()))){
            mFollow.setText(String.valueOf(userInfoData.getFollowCount()));
        }

        showNormal();
    }

    @Override
    public void showUserPublishData(List<CourseData> courseData){

        //设置教程
        if(courseData.get(0)!=null){
        CourseData course1 = courseData.get(0);
            //设置封面
            if(!TextUtils.isEmpty(course1.getCourseCover())){
                ImageLoader.loadToNIV(_mActivity, course1.getCourseCover(), mCourse1Image);
            }
            //设置标题
            if(!TextUtils.isEmpty(course1.getCourseTitle())){
                mCourse1Title.setText(course1.getCourseTitle());
            }
            //设置简介
            if(!TextUtils.isEmpty(course1.getCourseIntro())){
                mCourse1intro.setText(course1.getCourseIntro());
            }
            //设置点击跳转
            mCourse1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, v, getString(R.string.share_view));
                    JudgeUtils.startCourseDetailActivity(_mActivity,
                            options,
                            course1.getCourseId(),
                            course1.getCourseTitle()
                    );

                }
            });
        }

        if(courseData.get(1)!=null){
            CourseData course2 = courseData.get(1);
            //设置封面
            if(!TextUtils.isEmpty(course2.getCourseCover())){
                ImageLoader.loadToNIV(_mActivity, course2.getCourseCover(), mCourse2Image);
            }
            //设置标题
            if(!TextUtils.isEmpty(course2.getCourseTitle())){
                mCourse2Title.setText(course2.getCourseTitle());
            }
            //设置简介
            if(!TextUtils.isEmpty(course2.getCourseIntro())){
                mCourse2intro.setText(course2.getCourseIntro());
            }
            //设置点击跳转

            mCourse2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, v, getString(R.string.share_view));
                    JudgeUtils.startCourseDetailActivity(_mActivity,
                            options,
                            course2.getCourseId(),
                            course2.getCourseTitle()
                    );

                }
            });
        }



        showNormal();


    };

    @Override
    public void showUserPublishAlbum(List<AlbumCoverData> albumCoverData){

        if(albumCoverData.get(0)!=null){
            AlbumCoverData album1 = albumCoverData.get(0);
            if(!TextUtils.isEmpty(album1.getAlbumPic())){
                ImageLoader.loadToNIV(_mActivity, album1.getAlbumPic(), mAlbum1Image);
            }
            if(!TextUtils.isEmpty(album1.getAlbumName())){
                mAlbum1Title.setText(album1.getAlbumName());
            }
            //设置点击跳转
            mAlbum1Image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, v, getString(R.string.share_view));
                    JudgeUtils.startAlbumDetailActivity(_mActivity,
                            options,
                            album1.getAlbumId()
                    );
                }
            });
        }

        if(albumCoverData.get(1)!=null){
            AlbumCoverData album2 = albumCoverData.get(1);
            if(!TextUtils.isEmpty(album2.getAlbumPic())){
                ImageLoader.loadToNIV(_mActivity, album2.getAlbumPic(), mAlbum2Image);
            }
            if(!TextUtils.isEmpty(album2.getAlbumName())){
                mAlbum2Title.setText(album2.getAlbumName());
            }
            mAlbum2Image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, v, getString(R.string.share_view));
                    JudgeUtils.startAlbumDetailActivity(_mActivity,
                            options,
                            album2.getAlbumId()
                    );
                }
            });
        }

        if(albumCoverData.get(2)!=null){
            AlbumCoverData album3 = albumCoverData.get(2);
            if(!TextUtils.isEmpty(album3.getAlbumPic())){
                ImageLoader.loadToNIV(_mActivity, album3.getAlbumPic(), mAlbum3Image);
            }
            if(!TextUtils.isEmpty(album3.getAlbumName())){
                mAlbum3Title.setText(album3.getAlbumName());
            }

        }



        showNormal();

    }

    public void setListener(){
        mAlbumMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMore = new Intent(_mActivity, MyPublishAlbumActivity.class);
                startActivity(intentToMore);
            }
        });
        //添加点击更多
        mCourseMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMore = new Intent(_mActivity, MyPublishCourseActivity.class);
                startActivity(intentToMore);
            }
        });
        //
        mMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMore = new Intent(_mActivity, MessageActivity.class);
                startActivity(intentToMore);
            }
        });



    }


}