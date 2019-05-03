package com.example.handy.view.viewHolder;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LevelViewHolder extends BaseViewHolder {
    @BindView(R.id.publish_level_1)
    Button level1;

    @BindView(R.id.publish_level_2)
    Button level2;

    @BindView(R.id.publish_level_3)
    Button level3;

    @BindView(R.id.publish_level_4)
    Button level4;

    private int level = 0;

    public LevelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

        level1.setOnTouchListener(new MyButtonOnTouchListener(1));
        level2.setOnTouchListener(new MyButtonOnTouchListener(2));
        level3.setOnTouchListener(new MyButtonOnTouchListener(3));
        level4.setOnTouchListener(new MyButtonOnTouchListener(4));


    }

    private class MyButtonOnTouchListener implements View.OnTouchListener{
        private int index;
        private boolean isPressed = false;

        public MyButtonOnTouchListener(int index){
            this.index = index;
        }
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                return true;
            level = index;
            if(isPressed) {
                ((Button) view).setPressed(false);
                ((Button) view).setTextColor(view.getContext().getResources().getColor(R.color.publish_course_button_orange));
                isPressed = false;
            }
            else {
                ((Button) view).setPressed(true);
                ((Button) view).setTextColor(view.getContext().getResources().getColor(R.color.white));
                isPressed = true;
            }
            return true;
        }
    }

    public int getLevel(){
        return level;
    }
}
