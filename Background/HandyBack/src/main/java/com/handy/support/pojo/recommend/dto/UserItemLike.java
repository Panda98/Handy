package com.handy.support.pojo.recommend.dto;

import java.util.Date;

public class UserItemLike {
    private Integer userId;
    private Integer itemId;
    private Float preference;
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public float getPreference() {
        return preference;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setPreference(float preference) {
        this.preference = preference;
    }
}
