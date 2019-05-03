package com.example.handy.view.viewHolder;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelViewHolder extends BaseViewHolder {
    @BindView(R.id.publish_level_1)
    RadioButton level1;

    @BindView(R.id.publish_level_2)
    RadioButton level2;

    @BindView(R.id.publish_level_3)
    RadioButton level3;

    @BindView(R.id.publish_level_4)
    RadioButton level4;

    private int level = 0;

    List<RadioButton> buttons;

    public LevelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

//        level1.setOnTouchListener(new MyButtonOnTouchListener(1));
//        level2.setOnTouchListener(new MyButtonOnTouchListener(2));
//        level3.setOnTouchListener(new MyButtonOnTouchListener(3));
//        level4.setOnTouchListener(new MyButtonOnTouchListener(4));

        buttons = new ArrayList<>();
        buttons.add(level1);
        buttons.add(level2);
        buttons.add(level3);
        buttons.add(level4);

    }


    private class MyButtonOnTouchListener implements View.OnTouchListener{
        private int index;

        public MyButtonOnTouchListener(int index){
            this.index = index;
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                return true;
            level = index;
            if(view.getTag().equals("true")) {
                ((Button) view).setPressed(false);
                ((Button) view).setTextColor(view.getContext().getResources().getColor(R.color.publish_course_button_orange));
                view.setTag("false");
            }
            else {
                ((Button) view).setPressed(true);
                ((Button) view).setTextColor(view.getContext().getResources().getColor(R.color.white));
                view.setTag("true");

                for(int i=1;i<=4;i++){
                    if(i == index)
                        break;
                    buttons.get(i).setPressed(false);
                    buttons.get(i).setTextColor(view.getContext().getResources().getColor(R.color.publish_course_button_orange));
                    buttons.get(i).setTag("false");
                }
            }
            return true;
        }
    }

    public int getLevel(){
        for(int i =0;i<buttons.size();i++){
            if(buttons.get(i).isChecked()) {
                level = i+1;
                break;
            }
        }

        return level;
    }
}
