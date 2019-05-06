package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.CommentViewHolder;
import com.example.handy.view.viewHolder.ReceiveCommentViewHolder;

import java.util.List;

public class ReceiveCommentAdapter extends BaseQuickAdapter<CommentMessageData, ReceiveCommentViewHolder> {
    public ReceiveCommentAdapter(int layoutResId, @Nullable List<CommentMessageData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ReceiveCommentViewHolder helper, CommentMessageData item) {
        // 设置用户头像
        if (!TextUtils.isEmpty(item.getUserPic())) {
            ImageLoader.load(mContext, item.getUserPic(), helper.getView(R.id.item_receive_message_user_image));
        }
        //设置用户名
        if (!TextUtils.isEmpty(item.getUserNickName())) {
            helper.setText(R.id.item_receive_message_user_name, item.getUserNickName());
        }
        // 设置发布时间
        if (!TextUtils.isEmpty(item.getCommentUpdateTime())) {
            helper.setText(R.id.item_receive_message_time, item.getCommentUpdateTime());
        }

        // 设置评论内容
        if (!TextUtils.isEmpty(item.getCommentContent())) {
            helper.setText(R.id.item_receive_message_content, item.getCommentContent());
        }
    }

}
