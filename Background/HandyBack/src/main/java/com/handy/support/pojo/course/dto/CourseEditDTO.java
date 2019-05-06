package com.handy.support.pojo.course.dto;

import com.handy.support.entity.*;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public class CourseEditDTO {
    private Integer userId;
    private String courseTitle;
    private String courseIntro;
    private String courseNote;
    private String courseCover;
    private Byte levelId;
    private List<Label> labelList;
    private String diyLabel;
    private List<Item> itemList;
    private List<Step> stepList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public Byte getLevelId() {
        return levelId;
    }

    public void setLevelId(Byte levelId) {
        this.levelId = levelId;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    public String getDiyLabel() {
        return diyLabel;
    }

    public void setDiyLabel(String diyLabel) {
        this.diyLabel = diyLabel;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }

    public CourseEditDTO() {
    }

    public CourseEditDTO(Integer userId, String courseTitle, String courseIntro, String courseNote, String courseCover, Byte levelId, List<Label> labelList, String diyLabel, List<Item> itemList, List<Step> stepList) {
        this.userId = userId;
        this.courseTitle = courseTitle;
        this.courseIntro = courseIntro;
        this.courseNote = courseNote;
        this.courseCover = courseCover;
        this.levelId = levelId;
        this.labelList = labelList;
        this.diyLabel = diyLabel;
        this.itemList = itemList;
        this.stepList = stepList;
    }
}
