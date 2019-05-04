package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.donkingliang.labels.LabelsView;
import com.example.handy.R;
import com.shehuan.niv.NiceImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAbstractViewHolder extends BaseViewHolder {

    @BindView(R.id.album_image)
    NiceImageView courseImage;
    @BindView(R.id.album_title)
    TextView courseTitle;
    @BindView(R.id.album_description)
    TextView courseDesc;
    @BindView(R.id.album_author)
    TextView courseAuthor;


    public AlbumAbstractViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
