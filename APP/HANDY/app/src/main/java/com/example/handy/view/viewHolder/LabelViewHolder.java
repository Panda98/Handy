package com.example.handy.view.viewHolder;

import android.content.res.ColorStateList;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.example.handy.core.bean.LabelData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LabelViewHolder extends BaseViewHolder {
    @BindView(R.id.publish_course_labels)
    LinearLayout view;

    @BindView(R.id.labels_preinstall)
    public GridLayout preinstallView;

    @BindView(R.id.publish_course_custom_label)
    public EditText customLabelEd;

    @BindView(R.id.publish_choose_labels_btn)
    public Button chooseLabelsBtn;



    public LabelViewHolder(View view,List<LabelData> labelData) {
        super(view);
        ButterKnife.bind(this, view);

    }
    public void addButton(List<LabelData> choosedLabels){
        preinstallView.removeAllViews();
        for(int i = 0;i<choosedLabels.size();i++){
            CheckBox button = new CheckBox(preinstallView.getContext());
            button.setText(choosedLabels.get(i).getLabelName());
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            button.setTextColor(preinstallView.getResources().getColorStateList(R.color.label_button_text));
            button.setBackgroundResource(R.drawable.publish_course_level_btn);
            button.setButtonDrawable(null);
            button.setPadding(30,30,30,30);
            button.setChecked(true);
            button.setEnabled(false);
//            button.setOnTouchListener(new MyLabelsButtonListener(i));

            GridLayout.Spec rowSpec = GridLayout.spec(0);
            GridLayout.Spec columnSpec = GridLayout.spec(i);
            int columnCount = preinstallView.getColumnCount();
            if(i>columnCount-1){
                rowSpec = GridLayout.spec(i/columnCount);
                columnSpec = GridLayout.spec(i%columnCount);
            }
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
            params.setMargins(15,0,15,15);



            preinstallView.addView(button,params);
        }
    }


    public String getCustomLabel() {
        return customLabelEd.getText().toString();
    }
}
