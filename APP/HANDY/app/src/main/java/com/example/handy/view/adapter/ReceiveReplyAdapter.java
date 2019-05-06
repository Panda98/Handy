package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.LikeMessageData;
import com.example.handy.core.bean.ReplyMessageData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.ReceiveLikeViewHolder;
import com.example.handy.view.viewHolder.ReceiveReplyViewHolder;

import java.util.List;

public class ReceiveReplyAdapter extends BaseQuickAdapter<ReplyMessageData, ReceiveReplyViewHolder> {
    public ReceiveReplyAdapter(int layoutResId, @Nullable List<ReplyMessageData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ReceiveReplyViewHolder helper, ReplyMessageData item) {
        // 设置用户头像
        if (!TextUtils.isEmpty(item.getToReplyUserPic())) {
            ImageLoader.load(mContext, item.getToReplyUserPic(), helper.getView(R.id.item_receive_reply_user_image));
        }
        //设置用户名
        if (!TextUtils.isEmpty(item.getToReplyNickName())) {
            helper.setText(R.id.item_receive_reply_user_name, item.getToReplyNickName());
        }


        // 设置教程名
        if (!TextUtils.isEmpty(item.getInCourseTitle())) {
            String content = "在《" + item.getInCourseTitle() + "》中回复我：";
            helper.setText(R.id.item_receive_reply_course, content);
        }

        //设置回复内容
        if (!TextUtils.isEmpty(item.getReplyContent())) {
            helper.setText(R.id.item_receive_reply_content, item.getReplyContent());
        }

        //设置被回复的评论
        if (!TextUtils.isEmpty(item.getToReplyContent())) {
            String content = "我的评论" + item.getToReplyContent();
            helper.setText(R.id.item_receive_reply_comment, content);
        }

        //设置时间
        if (!TextUtils.isEmpty(item.getToReplyUpdateTime())) {
            helper.setText(R.id.item_receive_reply_time, item.getToReplyUpdateTime());
        }

    }

}

