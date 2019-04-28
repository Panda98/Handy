package com.example.handy.view.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepViewHolder extends BaseViewHolder {
    @BindView(R.id.step_number_text)
    TextView stepNumText;
    @BindView(R.id.step_picture_upload_btn)
    Button picUploadBtn;
    @BindView(R.id.step_add_step_explain_btn)
    Button stepExplainBtn;
    @BindView(R.id.step_explain_ed)
    EditText explainEd;


    public StepViewHolder(View view){
        super(view);
        ButterKnife.bind(this, view);
    }

    public Button getPicUploadBtn() {
        return picUploadBtn;
    }

    public Button getStepExplainBtn() {
        return stepExplainBtn;
    }

    public EditText getExplainEd() {
        return explainEd;
    }
}
