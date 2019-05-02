package com.example.handy.core.bean;

import java.util.List;

public class CourseDetailData {

    private int courseId;
    private String courseTitle;
    private String courseIntro;
    private String courseNote;
    private String courseCover;
    private int courseViews;
    private int courseCollects;
    private int courseLikes;
    private int userId;
    private String nickName;
    private String userPic;
    private int levelId;
    private List<LabelData> labelList;
    private String diyLabel;
    private String updateTime;
    private List<ItemData> itemList;
    private List<StepData> stepList;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getCourseViews() {
        return courseViews;
    }

    public void setCourseViews(int courseViews) {
        this.courseViews = courseViews;
    }

    public int getCourseCollects() {
        return courseCollects;
    }

    public void setCourseCollects(int courseCollects) {
        this.courseCollects = courseCollects;
    }

    public int getCourseLikes() {
        return courseLikes;
    }

    public void setCourseLikes(int courseLikes) {
        this.courseLikes = courseLikes;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ItemData> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemData> itemList) {
        this.itemList = itemList;
    }

    public List<StepData> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepData> stepList) {
        this.stepList = stepList;
    }
}
