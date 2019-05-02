package com.handy.support.pojo.Follow.dto;

import com.handy.support.entity.Label;

import java.util.Date;
import java.util.List;

public class CourseBrief {
    private int courseId;
         private  String courseTitle;
         private String courseCover;
         private String diyLabel;
         private Date updateTime;
         private Byte levelId;
         private String courseIntro;
        private List<Label> labelList;

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

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
