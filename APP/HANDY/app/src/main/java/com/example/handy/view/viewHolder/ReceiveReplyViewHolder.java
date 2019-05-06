package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveReplyViewHolder extends BaseViewHolder {
    @BindView(R.id.item_receive_reply_user_image)
    NiceImageView userImage;
    @BindView(R.id.item_receive_reply_user_name)
    TextView userName;
    @BindView(R.id.item_receive_reply_content)
    TextView replyContent;
    @BindView(R.id.item_receive_reply_course)
    TextView courseName;
    @BindView(R.id.item_receive_reply_comment)
    TextView replyComment;


    public ReceiveReplyViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
