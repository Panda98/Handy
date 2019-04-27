package com.example.handy.core.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {
    public static final int RELATIVELAYOUT = 1;
    public static final int TEXTVIEW = 2;
    public static final int RECYCLEVIEW = 3;

    private int itemType;

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
