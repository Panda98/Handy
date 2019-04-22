package com.handy.support.pojo.Follow.vo;

import java.util.Date;

public class CourseBrief {
    private int courseId;
         private  String courseTitle;
         private String courseCover;
         private String diyLabel;
         private Date updateTime;
         private Byte levelId;

    public Byte getLevelId() {
        return levelId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getDiyLabel() {
        return diyLabel;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setDiyLabel(String diyLabel) {
        this.diyLabel = diyLabel;
    }

    public void setLevelId(Byte levelId) {
        this.levelId = levelId;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
