package com.handy.support.pojo.Message.dto;

public class FavorBrief {
    private Integer userId;
    private Integer courseId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseId() {
        return courseId;
    }
}
