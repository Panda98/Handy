package com.example.handy.core.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {
    public static final int METERIAL_BTN_VIEW = 1;
    public static final int STEP_VIEW = 2;
    public static final int STEP_ITEM = 3;
    public static final int MATERIAL_ITEM = 4;
    public static final int STEP_BTN_VIEW = 5;
    public static final int TIPS_TEXT = 6;
    public static final int HEADER = 7;
    public static final int LABEL_VIEW = 8;




    private int itemType;

    private int index;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    public MultipleItem(int itemType,int index){
        this(itemType);
        this.index = index;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getIndex(){
        return index;
    }
}
