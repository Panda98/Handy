package com.example.handy.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.handy.R;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.utils.ImageLoader;
import com.example.handy.view.viewHolder.AlbumViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomepageAlbumAdapter extends ArrayAdapter<AlbumCoverData> {

    private Context mContext;
    private int layoutResourceId;
    private List<AlbumCoverData> mGridData = new ArrayList<AlbumCoverData>();

    public HomepageAlbumAdapter(Context context, int layoutResId, List<AlbumCoverData> data) {
        super(context, layoutResId, data);
        this.mContext = context;
        this.layoutResourceId = layoutResId;
        this.mGridData = data;
    }

    public void setGridData(List<AlbumCoverData> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    public List<AlbumCoverData> getData() {
        return this.mGridData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        convertView = inflater.inflate(layoutResourceId, parent, false);
        AlbumViewHolder holder = new AlbumViewHolder(convertView);

        //设置描述
        if (!TextUtils.isEmpty(mGridData.get(position).getAlbumDetail())) {
            holder.setText(R.id.recommend_album_text, mGridData.get(position).getAlbumDetail());
        }

        // 设置图片
        if (!TextUtils.isEmpty(mGridData.get(position).getAlbumPic())) {
            ImageLoader.loadToNIV(mContext, mGridData.get(position).getAlbumPic(), holder.getView(R.id.recommend_album_image));
        }

        return convertView;
    }
}
