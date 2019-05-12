package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.labels.LabelsView;
import com.example.handy.R;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.LabelData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.CourseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecommendCourseAdapter extends BaseQuickAdapter<CourseData, CourseViewHolder> {

    private boolean isMainPage;


    public RecommendCourseAdapter(int layoutResId, @Nullable List<CourseData> data) {
        super(layoutResId, data);
    }

    public void isCollectPage() {
        isMainPage = true;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(CourseViewHolder helper, CourseData item) {
        // 设置标题
        if (!TextUtils.isEmpty(item.getCourseTitle())) {
            helper.setText(R.id.recommend_course_title, item.getCourseTitle());
        }
        //设置描述
        if (!TextUtils.isEmpty(item.getCourseIntro())) {
            helper.setText(R.id.recommend_course_description, item.getCourseIntro());
        }
        // 设置作者
        if (!TextUtils.isEmpty(item.getAuthorName())) {
            helper.setText(R.id.course_author, item.getAuthorName());
        }

        // 设置图片
        if (!TextUtils.isEmpty(item.getCourseCover())) {
            ImageLoader.loadToNIV(mContext, item.getCourseCover(), helper.getView(R.id.recommend_course_image));
        }

        // 设置标签
        if (item.getLabelList() != null) {

            ArrayList<String> label = new ArrayList<>();

            for (LabelData labelData : item.getLabelList()) {
                if (labelData != null)
                    label.add(labelData.getLabelName());
            }

            LabelsView labelsView = (LabelsView) helper.getView(R.id.course_labels);
            labelsView.setLabels(label);
        }

    }
}
