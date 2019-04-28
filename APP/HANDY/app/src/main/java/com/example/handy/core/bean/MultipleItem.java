package com.example.handy.core.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {
    public static final int METERIAL_BTN_VIEW = 1;
    public static final int STEP_VIEW = 2;
    public static final int STEP_ITEM = 3;
    public static final int MATERIAL_ITEM = 4;
    public static final int COVER_PIC_VIEW = 5;
    public static final int TITLE = 6;
    public static final int MATERIAL_TEXT = 7;
    public static final int STEP_PIC_VIEW = 8;
    public static final int STEP_BTN_VIEW = 9;
    public static final int TIPS_TEXT = 10;
    public static final int HEADER = 11;



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
