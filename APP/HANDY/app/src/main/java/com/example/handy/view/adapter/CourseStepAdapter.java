package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.core.bean.StepData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.StepViewHolder;

import java.util.List;

public class CourseStepAdapter extends BaseQuickAdapter<StepData, StepViewHolder> {

    public CourseStepAdapter(int layoutResId, @Nullable List<StepData> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(StepViewHolder helper, StepData item) {

        // 设置步骤图片
        if (!TextUtils.isEmpty(item.getStepImg())) {
            helper.getView(R.id.course_detail_step_image).setVisibility(View.VISIBLE);
            ImageLoader.loadToNIV(mContext, item.getStepImg(), helper.getView(R.id.course_detail_step_image));
        }

        // 设置步骤
        if (!TextUtils.isEmpty(String.valueOf(item.getStepTag()))) {
            helper.setText(R.id.course_detail_step_num, String.valueOf(item.getStepTag()));
        }

        // 设置内容
        if (!TextUtils.isEmpty(item.getStepDetail())) {
            helper.setText(R.id.course_detail_step_content, item.getStepDetail());
        }

    }
}
