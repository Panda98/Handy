package com.example.handy.view.adapter;

import android.text.Editable;
import android.widget.Button;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.handy.R;
import com.example.handy.core.bean.MultipleItem;

import java.util.List;

public class CourseEditorMultiAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public CourseEditorMultiAdapter(List data){
        super(data);
        addItemType(MultipleItem.STEP_VIEW,R.layout.step_text_view);
        addItemType(MultipleItem.METERIAL_BTN_VIEW,R.layout.material_btn_view);
        addItemType(MultipleItem.STEP_ITEM,R.layout.item_step_view);
        addItemType(MultipleItem.HEADER,R.layout.publish_course_header);
        addItemType(MultipleItem.MATERIAL_ITEM,R.layout.item_material_view);
        addItemType(MultipleItem.STEP_BTN_VIEW,R.layout.step_btn_view);
        addItemType(MultipleItem.TIPS_TEXT,R.layout.publish_course_tips_view);


    }
    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item){
        helper.addOnClickListener(R.id.picture_upload)
        .addOnClickListener(R.id.add_material_row)
        .addOnClickListener(R.id.adjust_material)
        .addOnClickListener(R.id.add_step_row)
        .addOnClickListener(R.id.adjust_step)
        .addOnClickListener(R.id.step_picture_upload_btn)
        .addOnClickListener(R.id.material_name_edtext)
        .addOnClickListener(R.id.step_explain_ed)
        .addOnClickListener(R.id.publish_course_title_view)
        .addOnClickListener(R.id.publish_tips_ed);
        if(item.getItemType() == MultipleItem.STEP_ITEM){
            helper.setText(R.id.step_number_text,"步骤"+item.getIndex());
        }
    }

}
