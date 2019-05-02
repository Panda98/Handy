package com.example.handy.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.core.bean.StepData;
import com.example.handy.view.viewHolder.StepViewHolder;

import java.util.List;

public class CourseStepAdapter extends BaseQuickAdapter<StepData, StepViewHolder> {

    public CourseStepAdapter(int layoutResId, @Nullable List<StepData> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(StepViewHolder helper, StepData item) {

    }
}
