package com.example.handy.view.viewHolder;

import android.media.Image;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishStepViewHolder extends BaseViewHolder {

    @BindView(R.id.step_picture_upload)
    ImageView imageView;
    @BindView(R.id.step_pic_text)
    TextView textView;
    @BindView(R.id.step_explain_ed)
    EditText detailText;

    private int index;

    public PublishStepViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public ImageView getImageView(){
        return imageView;
    }
    public TextView getTextView(){
        return textView;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public EditText getDetailText() {
        return detailText;
    }
}
