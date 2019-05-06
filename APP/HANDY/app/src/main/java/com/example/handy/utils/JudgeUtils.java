package com.example.handy.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.handy.app.Constants;
import com.example.handy.view.activity.AlbumDetailActivity;
import com.example.handy.view.activity.AuthorHomepageActivity;
import com.example.handy.view.activity.CourseDetailActivity;
import com.example.handy.view.activity.CourseListByLabelActivity;
import com.example.handy.view.activity.MorePublishAlbumActivity;
import com.example.handy.view.activity.MorePublishCourseActivity;

/**
 * @author wangziang
 * @date 2019/04/28
 */

public class JudgeUtils {

    // 跳转到教程详情
    public static void startCourseDetailActivity(Context mActivity, ActivityOptions activityOptions, int courseId, String courseTitle) {

        Intent intent = new Intent(mActivity, CourseDetailActivity.class);
        intent.putExtra(Constants.COURSE_ID, courseId);
        intent.putExtra(Constants.COURSE_Title, courseTitle);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }

    }

    public static void startCourseDetailActivityWithoutOption(Context mActivity, int courseId, String courseTitle) {

        Intent intent = new Intent(mActivity, CourseDetailActivity.class);
        intent.putExtra(Constants.COURSE_ID, courseId);
        intent.putExtra(Constants.COURSE_Title, courseTitle);
        mActivity.startActivity(intent);

    }

    // 跳转到专辑详情
    public static void startAlbumDetailActivity(Context mActivity, ActivityOptions activityOptions, int albumId) {

        Intent intent = new Intent(mActivity, AlbumDetailActivity.class);
        intent.putExtra(Constants.ALBUM_ID, albumId);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }

    }

    public static void startAlbumDetailActivityWithoutOption(Context mActivity, int albumId) {

        Intent intent = new Intent(mActivity, AlbumDetailActivity.class);
        intent.putExtra(Constants.ALBUM_ID, albumId);
        mActivity.startActivity(intent);


    }

    // 跳转到作者主页
    public static void startAuthorHomepageActivity(Context mActivity, ActivityOptions activityOptions, int authorId) {

        Intent intent = new Intent(mActivity, AuthorHomepageActivity.class);
        intent.putExtra(Constants.AUTHOR_ID, authorId);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }

    public static void startAuthorHomepageActivityWithoutOption(Context mActivity, int authorId) {

        Intent intent = new Intent(mActivity, AuthorHomepageActivity.class);
        intent.putExtra(Constants.AUTHOR_ID, authorId);
        mActivity.startActivity(intent);

    }

    // 跳转到更多教程
    public static void startMoreCourseActivity(Context mActivity, ActivityOptions activityOptions, int userId) {

        Intent intent = new Intent(mActivity, MorePublishCourseActivity.class);
        intent.putExtra(Constants.USER_ID, userId);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }

    public static void startMoreCourseActivityWithoutOption(Context mActivity, int userId) {

        Intent intent = new Intent(mActivity, MorePublishCourseActivity.class);
        intent.putExtra(Constants.USER_ID, userId);
        mActivity.startActivity(intent);

    }

    // 跳转到更多专辑
    public static void startMoreAlbumActivity(Context mActivity, ActivityOptions activityOptions, int userId) {

        Intent intent = new Intent(mActivity, MorePublishAlbumActivity.class);
        intent.putExtra(Constants.USER_ID, userId);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }

    public static void startMoreAlbumActivityWithoutOption(Context mActivity, int userId) {

        Intent intent = new Intent(mActivity, MorePublishAlbumActivity.class);
        intent.putExtra(Constants.USER_ID, userId);
        mActivity.startActivity(intent);

    }

    // 跳转标签课程列表
    public static void startLabelCourseListActivity(Context mActivity, ActivityOptions activityOptions, int labelId) {

        Intent intent = new Intent(mActivity, CourseListByLabelActivity.class);
        intent.putExtra(Constants.LABEL_ID, labelId);

        if (activityOptions != null && !Build.MANUFACTURER.contains("samsung") && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.startActivity(intent, activityOptions.toBundle());
        } else {
            mActivity.startActivity(intent);
        }
    }
}
