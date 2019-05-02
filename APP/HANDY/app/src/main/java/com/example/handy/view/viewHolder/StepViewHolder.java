package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepViewHolder extends BaseViewHolder {

    @BindView(R.id.course_detail_step_num)
    TextView stepNum;
    @BindView(R.id.course_detail_step_content)
    TextView stepContent;
    @BindView(R.id.course_detail_step_image)
    NiceImageView stepImage;

    public StepViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
