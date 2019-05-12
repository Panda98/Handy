package com.example.handy.view.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.AlbumAbstractViewHolder;

import java.util.List;

public class AlbumCoverDataAdapter extends BaseQuickAdapter<AlbumCoverData, AlbumAbstractViewHolder> {

    public AlbumCoverDataAdapter(int layoutResId, @Nullable List<AlbumCoverData> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(AlbumAbstractViewHolder helper, AlbumCoverData item) {
        // 设置标题
        if (!TextUtils.isEmpty(item.getAlbumName())) {
            helper.setText(R.id.album_title, item.getAlbumName());
        }
        //设置描述
        if (!TextUtils.isEmpty(item.getAlbumDetail())) {
            helper.setText(R.id.album_description, item.getAlbumDetail());
        }
        // 设置作者
        if (!TextUtils.isEmpty(item.getAuthorName())) {
            helper.setText(R.id.album_author, item.getAuthorName());
        }

        // 设置图片
        if (!TextUtils.isEmpty(item.getAlbumPic())) {
            ImageLoader.loadToNIV(mContext, item.getAlbumPic(), helper.getView(R.id.album_image));
        }

    }
}
