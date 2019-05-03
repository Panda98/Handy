package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowViewHolder extends BaseViewHolder {

    @BindView(R.id.follow_user_image)
    NiceImageView userImage;
    @BindView(R.id.follow_user_name)
    TextView userName;
    @BindView(R.id.follow_user_publish_time)
    TextView publishTime;
    @BindView(R.id.follow_course_image)
    NiceImageView courseImage;
    @BindView(R.id.follow_course_title)
    TextView courseTitle;
    @BindView(R.id.follow_course_description)
    TextView courseDesc;

    public FollowViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
