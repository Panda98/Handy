package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumViewHolder extends BaseViewHolder {

    @BindView(R.id.recommend_album_image)
    ImageView albumImage;
    @BindView(R.id.recommend_album_text)
    TextView albumTitle;

    public AlbumViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
