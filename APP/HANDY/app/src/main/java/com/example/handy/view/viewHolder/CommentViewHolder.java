package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentViewHolder extends BaseViewHolder {

    @BindView(R.id.comment_user_image)
    NiceImageView userImage;
    @BindView(R.id.comment_user_name)
    TextView userName;
    @BindView(R.id.comment_publish_time)
    TextView publishTime;
    @BindView(R.id.comment_text)
    TextView commentText;
    @BindView(R.id.comment_reply_button)
    TextView commentReplyBtn;
    @BindView(R.id.comment_reply_num)
    TextView replyNum;

    public CommentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
