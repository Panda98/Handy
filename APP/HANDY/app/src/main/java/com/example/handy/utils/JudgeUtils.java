package com.example.handy.utils;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.example.handy.app.Constants;
import com.example.handy.view.activity.CourseDetailActivity;

/**
 * @author wangziang
 * @date 2019/04/28
 */

public class JudgeUtils {

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
}
