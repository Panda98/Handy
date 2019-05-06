package com.handy.support.pojo.course.dto;

/**
 * Created by joanie on 2019/5/5.
 */
public class ItemDTO {
    private String itemName;
    private String itemNumber;
    private Byte itemTag;


    public ItemDTO() {
    }

    public ItemDTO(String itemName, String itemNumber, Byte itemTag) {
        this.itemName = itemName;
        this.itemNumber = itemNumber;
        this.itemTag = itemTag;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Byte getItemTag() {
        return itemTag;
    }

    public void setItemTag(Byte itemTag) {
        this.itemTag = itemTag;
    }
}
