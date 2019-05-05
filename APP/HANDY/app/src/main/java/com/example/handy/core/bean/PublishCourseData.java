package com.example.handy.core.bean;

import java.io.File;
import java.util.List;

public class PublishCourseData {
    private int userId;
    private String courseTitle;
    private String courseIntro;
    private String courseNote;
    private String courseCover;
    private int levelId;
    private List<LabelData> labelList;
    private String diyLabel;
    private List<MaterialItemData> itemList;
    private List<CourseStepData> stepList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        this.courseNote = courseNote;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public List<LabelData> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<LabelData> labelList) {
        this.labelList = labelList;
    }

    public String getDiyLabel() {
        return diyLabel;
    }

    public void setDiyLabel(String diyLabel) {
        this.diyLabel = diyLabel;
    }

    public List<MaterialItemData> getItemList() {
        return itemList;
    }

    public void setItemList(List<MaterialItemData> itemList) {
        this.itemList = itemList;
    }

    public List<CourseStepData> getStepList() {
        return stepList;
    }

    public void setStepList(List<CourseStepData> stepList) {
        this.stepList = stepList;
    }

}
