package com.example.handy.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.core.bean.CourseStepData;
import com.example.handy.view.viewHolder.StepViewHolder;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.List;

public class CourseEditorStepAdapter extends BaseQuickAdapter<CourseStepData, StepViewHolder> {

    String explain;

    public CourseEditorStepAdapter(int layoutResId, @Nullable List<CourseStepData> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(StepViewHolder helper, CourseStepData item){
        helper.setText(R.id.step_number_text,R.string.step+item.getStepTag());

        Button picUploadBtn = helper.getPicUploadBtn();
        picUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISNav.getInstance().init(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, String path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                // 自由配置选项
                ISListConfig config = new ISListConfig.Builder()
                        // 是否多选, 默认true
                        .multiSelect(false)
                        // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                        .rememberSelected(false)
                        // “确定”按钮背景色
                        .btnBgColor(Color.GRAY)
                        // “确定”按钮文字颜色
                        .btnTextColor(Color.BLUE)
                        // 使用沉浸式状态栏
                        .statusBarColor(Color.parseColor("#3F51B5"))
                        // 标题
                        .title("图片")
                        // 标题文字颜色
                        .titleColor(Color.WHITE)
                        // TitleBar背景色
                        .titleBgColor(Color.parseColor("#3F51B5"))
                        // 裁剪大小。needCrop为true的时候配置
                        .cropSize(1, 1, 200, 200)
                        .needCrop(true)
                        // 第一个是否显示相机，默认true
                        .needCamera(true)
                        // 最大选择图片数量，默认9
                        .maxNum(1)
                        .build();

                // 跳转到图片选择器
                ISNav.getInstance().toListActivity(this, config, Constants.PIC_REQUET_CODE);

            }

        });

        Button addStepExplainBtn = helper.getStepExplainBtn();
        EditText explainEd = helper.getExplainEd();
        addStepExplainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explainEd.setVisibility(View.VISIBLE);
            }
        });

        explainEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                explain = editable.toString();
            }
        });

    }

}
