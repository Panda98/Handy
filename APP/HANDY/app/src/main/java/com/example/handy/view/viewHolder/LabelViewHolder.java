package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LabelViewHolder extends BaseViewHolder {
    @BindView(R.id.labels_preinstall)
    public LinearLayout preinstallView;
    @BindView(R.id.labels_custom)
    public LinearLayout customView;
    public LabelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        Button button = new Button(preinstallView.getContext());
        button.setText("布艺");
        preinstallView.addView(button);
    }


}
