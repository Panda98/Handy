package com.handy.support.pojo.course.vo;

import com.handy.support.entity.*;

import java.util.Date;
import java.util.List;
/**
 * Created by joanie on 2019/4/15.
 */
public class CourseVO {


    private Integer courseId;

    private String courseTitle;

    private String courseCover;

    private String nickName;

    private String userPic;

    private String courseIntro;

    private Byte levelId;

    private List<Label> labelList;

    private String diyLabel;

    private Date updateTime;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
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

    public CourseVO() {
    }

    public CourseVO(Integer courseId,String courseTitle, String courseCover, String nickName, String userPic, String courseIntro, Byte levelId, List<Label> labelList, String diyLabel, Date updateTime) {
        this.courseId=courseId;
        this.courseTitle = courseTitle;
        this.courseCover = courseCover;
        this.nickName = nickName;
        this.userPic = userPic;
        this.courseIntro = courseIntro;
        this.levelId = levelId;
        this.labelList = labelList;
        this.diyLabel = diyLabel;
        this.updateTime = updateTime;
    }
}
