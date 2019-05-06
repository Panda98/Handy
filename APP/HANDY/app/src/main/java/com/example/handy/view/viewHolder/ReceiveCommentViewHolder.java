package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveCommentViewHolder extends BaseViewHolder {
    @BindView(R.id.item_receive_message_user_image)
    NiceImageView userImage;
    @BindView(R.id.item_receive_message_content)
    TextView messageContent;
    @BindView(R.id.item_receive_message_user_name)
    TextView userName;
    @BindView(R.id.item_receive_message_time)
    TextView messageTime;


    public ReceiveCommentViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
