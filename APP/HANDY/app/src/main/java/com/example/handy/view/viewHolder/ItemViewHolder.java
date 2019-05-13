package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemViewHolder extends BaseViewHolder {

    @BindView(R.id.course_detail_item_name)
    TextView itemName;

    public ItemViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
