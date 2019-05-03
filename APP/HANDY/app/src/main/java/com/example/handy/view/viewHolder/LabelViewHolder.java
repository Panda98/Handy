package com.example.handy.view.viewHolder;

import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.labels_preinstall)
    public GridLayout preinstallView;

    @BindView(R.id.publish_course_custom_label)
    public EditText customLabelEd;

    private List<Integer> selectedIndex;

    private List<LabelData> labelData;
    private String customLabel;


    public LabelViewHolder(View view,List<LabelData> labelData) {
        super(view);
        ButterKnife.bind(this, view);

        selectedIndex = new ArrayList<>();
        this.labelData = labelData;

//        GridLayoutManager manager = new GridLayoutManager(preinstallView.getContext(),5);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//            }
//        });


        addButton();
        customLabelEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                customLabel = editable.toString();
            }
        });

    }

    private void addButton(){
        for(int i = 0;i<labelData.size();i++){
            Button button = new Button(preinstallView.getContext());
            button.setText(labelData.get(i).getLabelName());
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            button.setTextColor(preinstallView.getContext().getResources().getColor(R.color.publish_course_button_orange));
            button.setBackgroundResource(R.drawable.label_button);
            button.setOnTouchListener(new MyLabelsButtonListener(i));

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


    public class MyLabelsButtonListener implements View.OnTouchListener {
        private int index;
        private boolean isPressed;
        public MyLabelsButtonListener(int index){
            super();
            this.index = index;
            isPressed = false;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                return true;
            if(isPressed) {
                ((Button) view).setPressed(false);
                ((Button) view).setTextColor(preinstallView.getContext().getResources().getColor(R.color.publish_course_button_orange));
                selectedIndex.remove(Integer.valueOf(index));
                isPressed = false;
            }
            else {
                ((Button) view).setPressed(true);
                ((Button) view).setTextColor(preinstallView.getContext().getResources().getColor(R.color.white));
                selectedIndex.add(index);
                isPressed = true;
            }
            return true;
        }
    }

    public String getCustomLabel() {
        return customLabelEd.getText().toString();
    }

    public List<LabelData> getSelectedLabel() {
        List<LabelData> selected = new ArrayList<>();
        for(int i = 0;i<selectedIndex.size();i++){
            LabelData label = labelData.get(selectedIndex.get(i));
            selected.add(label);
        }
        return selected;
    }
}
