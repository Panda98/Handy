package com.handy.support.pojo.course.vo;

import com.handy.support.entity.*;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public class CourseSimpleVO {
    private Integer courseId;
    private String courseTitle;
    private String courseCover;
    private String courseIntro;
    private String authorName;
    private Byte levelId;
    private List<Label> labelList;
    private String diyLabel;

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public CourseSimpleVO() {
    }

    public CourseSimpleVO(Integer courseId, String courseTitle, String courseCover, String courseIntro, String authorName, Byte levelId, List<Label> labelList, String diyLabel) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseCover = courseCover;
        this.courseIntro = courseIntro;
        this.authorName = authorName;
        this.levelId = levelId;
        this.labelList = labelList;
        this.diyLabel = diyLabel;
    }
}
