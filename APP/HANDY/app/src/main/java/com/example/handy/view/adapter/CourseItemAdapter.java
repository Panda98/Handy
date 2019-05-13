package com.example.handy.view.adapter;

import android.content.ClipData;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.ItemData;
import com.example.handy.core.bean.StepData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.ItemViewHolder;
import com.example.handy.view.viewHolder.StepViewHolder;

import java.util.List;

public class CourseItemAdapter extends BaseQuickAdapter<ItemData, ItemViewHolder> {

    public CourseItemAdapter(int layoutResId, @Nullable List<ItemData> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(ItemViewHolder helper, ItemData item) {

        // 设置材料名称
        if (!TextUtils.isEmpty(item.getItemName())) {
            helper.setText(R.id.course_detail_item_name, String.valueOf(item.getItemName()));

        }

    }
}
