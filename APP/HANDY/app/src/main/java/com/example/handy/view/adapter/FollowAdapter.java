package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.FollowViewHolder;

import java.util.List;

public class FollowAdapter extends BaseQuickAdapter<FollowData, FollowViewHolder> {

    public FollowAdapter(int layoutResId, @Nullable List<FollowData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(FollowViewHolder helper, FollowData item) {

        // 设置作者头像
        if (!TextUtils.isEmpty(item.getAuthorImage())) {
            ImageLoader.load(mContext, item.getAuthorImage(), helper.getView(R.id.follow_user_image));
        }
        //设置作者名
        if (!TextUtils.isEmpty(item.getAuthorName())) {
            helper.setText(R.id.follow_user_name, item.getAuthorName());
        }
        // 设置发布时间
        if (!TextUtils.isEmpty(item.getPublishTime())) {
            helper.setText(R.id.follow_user_publish_time, item.getPublishTime());
        }

        // 设置教程图片
        if (!TextUtils.isEmpty(item.getCourseCover())) {
            ImageLoader.load(mContext, item.getCourseCover(), helper.getView(R.id.follow_course_image));
        }

        // 设置教程名
        if (!TextUtils.isEmpty(item.getCourseTitle())) {
            helper.setText(R.id.follow_course_title, item.getCourseTitle());
        }

        // 设置教程描述
        if (!TextUtils.isEmpty(item.getCourseIntro())) {
            helper.setText(R.id.follow_course_description, item.getCourseIntro());
        }

    }
}
