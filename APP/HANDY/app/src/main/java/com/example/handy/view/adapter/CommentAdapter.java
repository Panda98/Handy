package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.CommentData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.CommentViewHolder;

import org.w3c.dom.Comment;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<CommentData, CommentViewHolder> {


    public CommentAdapter(int layoutResId, @Nullable List<CommentData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(CommentViewHolder helper, CommentData item) {
        // 设置用户头像
        if (!TextUtils.isEmpty(item.getUserPic())) {
            ImageLoader.load(mContext, item.getUserPic(), helper.getView(R.id.comment_user_image));
        }
        //设置用户名
        if (!TextUtils.isEmpty(item.getNickName())) {
            helper.setText(R.id.comment_user_name, item.getNickName());
        }
        // 设置发布时间
        if (!TextUtils.isEmpty(item.getUpdateTime())) {
            helper.setText(R.id.comment_publish_time, item.getUpdateTime());
        }

        // 设置评论内容
        if (!TextUtils.isEmpty(item.getCommentContent())) {
            helper.setText(R.id.comment_text, item.getCommentContent());
        }

        // 设置回复数量
        //if (!TextUtils.isEmpty(item.getCourseIntro())) {
        //    helper.setText(R.id.follow_course_description, item.getCourseIntro());
        //}
    }
}
