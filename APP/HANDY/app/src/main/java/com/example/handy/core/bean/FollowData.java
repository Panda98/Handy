package com.example.handy.core.bean;

import java.util.List;

public class FollowData {

    private int courseId;
    private String courseTitle;
    private String courseCover;
    private String courseIntro;
    private List<LabelData> labelList;
    private String diyLabel;
    private int authorId;
    private String authorName;
    private String authorImage;
    private String publishTime;

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

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
