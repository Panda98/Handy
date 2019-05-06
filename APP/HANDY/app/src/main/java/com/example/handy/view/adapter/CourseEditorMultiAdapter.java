package com.example.handy.view.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handy.R;
import com.example.handy.core.bean.LabelData;
import com.example.handy.core.bean.MultipleItem;
import com.example.handy.view.viewHolder.LabelViewHolder;
import com.example.handy.view.viewHolder.LevelViewHolder;
import com.example.handy.view.viewHolder.PublishStepViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CourseEditorMultiAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private List<LabelData> choosedlLabels;
    private LabelViewHolder labelViewHolder;
    private LevelViewHolder levelViewHolder;
    private List<PublishStepViewHolder> stepViewHolders;

    public CourseEditorMultiAdapter(List data){
        super(data);
        addItemType(MultipleItem.STEP_VIEW,R.layout.step_text_view);
        addItemType(MultipleItem.METERIAL_BTN_VIEW,R.layout.material_btn_view);
        addItemType(MultipleItem.STEP_ITEM,R.layout.item_step_view);
        addItemType(MultipleItem.HEADER,R.layout.publish_course_header);
        addItemType(MultipleItem.MATERIAL_ITEM,R.layout.item_material_view);
        addItemType(MultipleItem.STEP_BTN_VIEW,R.layout.step_btn_view);
        addItemType(MultipleItem.TIPS_TEXT,R.layout.publish_course_tips_view);
        addItemType(MultipleItem.LABEL_VIEW,R.layout.publish_course_labels);
        addItemType(MultipleItem.LEVEL_VIEW,R.layout.publish_course_level_view);


        stepViewHolders = new ArrayList<>();


    }
    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item){
        helper.addOnClickListener(R.id.picture_upload)
        .addOnClickListener(R.id.add_material_row)
        .addOnClickListener(R.id.adjust_material)
        .addOnClickListener(R.id.add_step_row)
        .addOnClickListener(R.id.adjust_step)
        .addOnClickListener(R.id.step_picture_upload)
        .addOnClickListener(R.id.material_name_edtext)
        .addOnClickListener(R.id.step_explain_ed)
        .addOnClickListener(R.id.publish_course_title_view)
        .addOnClickListener(R.id.publish_tips_ed)
        .addOnClickListener(R.id.publish_course_intro)
        .addOnClickListener(R.id.publish_choose_labels_btn);
        if(item.getItemType() == MultipleItem.STEP_ITEM){
            helper.setText(R.id.step_number_text,"步骤"+item.getIndex());
            ((PublishStepViewHolder)helper).setIndex(item.getIndex());
            PublishStepViewHolder viewHolder = (PublishStepViewHolder) helper;
            if(!stepViewHolders.contains(helper))
                stepViewHolders.add((PublishStepViewHolder)helper);
        }
        if(item.getItemType() == MultipleItem.LABEL_VIEW)
            labelViewHolder = (LabelViewHolder) helper;
        if (item.getItemType() == MultipleItem.LEVEL_VIEW)
            levelViewHolder = (LevelViewHolder) helper;

    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType){
        if(viewType == MultipleItem.LABEL_VIEW){
            LabelViewHolder viewHolder = new LabelViewHolder(getItemView(R.layout.publish_course_labels,parent), choosedlLabels);
            return viewHolder;
        }else if (viewType == MultipleItem.LEVEL_VIEW){
            LevelViewHolder viewHolder = new LevelViewHolder(getItemView(R.layout.publish_course_level_view,parent));
            return viewHolder;
        }else if(viewType == MultipleItem.STEP_ITEM){
            PublishStepViewHolder viewHolder = new PublishStepViewHolder(getItemView(R.layout.item_step_view,parent));
            return viewHolder;
        }
        return super.onCreateDefViewHolder(parent,viewType);
    }

    public LabelViewHolder getLabelViewHolder(){
        return labelViewHolder;
    }

    public LevelViewHolder getLevelViewHolder(){
        return levelViewHolder;
    }
    public List<PublishStepViewHolder> getStepViewHolders(){
        return stepViewHolders;
    }

}
