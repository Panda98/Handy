package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.LikeMessageData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.ReceiveCommentViewHolder;
import com.example.handy.view.viewHolder.ReceiveLikeViewHolder;

import java.util.List;

public class ReceiveLikeAdapter extends BaseQuickAdapter<LikeMessageData, ReceiveLikeViewHolder> {
    public ReceiveLikeAdapter(int layoutResId, @Nullable List<LikeMessageData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ReceiveLikeViewHolder helper, LikeMessageData item) {
        // 设置用户头像
        if (!TextUtils.isEmpty(item.getUserPic())) {
            ImageLoader.load(mContext, item.getUserPic(), helper.getView(R.id.item_receive_message_user_image));
        }
        //设置用户名
        if (!TextUtils.isEmpty(item.getUserNickName())) {
            helper.setText(R.id.item_receive_message_user_name, item.getUserNickName());
        }


        // 设置评论内容
        if (!TextUtils.isEmpty(item.getCourseTitle())) {
            String content = "给你的作品《" + item.getCourseTitle() + "》点了个赞";
            helper.setText(R.id.item_receive_message_content, content);
        }
    }

}
