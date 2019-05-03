package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseViewHolder extends BaseViewHolder {

    @BindView(R.id.recommend_course_image)
    NiceImageView courseImage;
    @BindView(R.id.recommend_course_title)
    TextView courseTitle;
    @BindView(R.id.recommend_course_description)
    TextView courseDesc;
    @BindView(R.id.recommend_course_author)
    TextView courseAuthor;


    public CourseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
