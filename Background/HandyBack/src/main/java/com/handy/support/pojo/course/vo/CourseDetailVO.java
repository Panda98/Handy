package com.handy.support.pojo.course.vo;

import com.handy.support.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public class CourseDetailVO {
    private Integer courseId;
    private String courseTitle;
    private String courseIntro;
    private String courseNote;
    private String courseCover;
    private Integer courseViews;
    private Integer courseCollects;
    private Integer courseLikes;
    private Integer userId;
    private String nickName;
    private String userPic;
    private Byte levelId;
    private List<Label> labelList;
    private String diyLabel;
    private Date updateTime;
    private List<Item> itemList;
    private List<Step> stepList;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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

    public Integer getCourseViews() {
        return courseViews;
    }

    public void setCourseViews(Integer courseViews) {
        this.courseViews = courseViews;
    }

    public Integer getCourseLikes() {
        return courseLikes;
    }

    public void setCourseLikes(Integer courseLikes) {
        this.courseLikes = courseLikes;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCourseCollects() {
        return courseCollects;
    }

    public void setCourseCollects(Integer courseCollects) {
        this.courseCollects = courseCollects;
    }

    public CourseDetailVO() {
    }

    public CourseDetailVO(Integer courseId,String courseTitle, String courseIntro, String courseNote, String courseCover, Integer courseViews, Integer courseCollects, Integer courseLikes, Integer userId, String nickName, String userPic, Byte levelId, List<Label> labelList, String diyLabel, Date updateTime, List<Item> itemList, List<Step> stepList) {
        this.courseId=courseId;
        this.courseTitle = courseTitle;
        this.courseIntro = courseIntro;
        this.courseNote = courseNote;
        this.courseCover = courseCover;
        this.courseViews = courseViews;
        this.courseCollects = courseCollects;
        this.courseLikes = courseLikes;
        this.userId = userId;
        this.nickName = nickName;
        this.userPic = userPic;
        this.levelId = levelId;
        this.labelList = labelList;
        this.diyLabel = diyLabel;
        this.updateTime = updateTime;
        this.itemList = itemList;
        this.stepList = stepList;
    }
}
