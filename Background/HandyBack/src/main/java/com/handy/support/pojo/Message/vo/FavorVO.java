package com.handy.support.pojo.Message.vo;

import com.handy.support.entity.Label;
import com.handy.support.pojo.Message.dto.FavorDTO;

import java.util.Date;
import java.util.List;

public class FavorVO {
    private Integer userId;
    private Integer courseId;
    private  String courseTitle;
    private String courseCover;
    private String courseDiyLabel;
    private Date courseUpdateTime;
    private Byte courseLevelId;
    private String courseIntro;
    private List<Label> courseLabelList;
    private String userNickName;
    private String userPic;
    public FavorVO(FavorDTO dto){
        this.courseCover=dto.getCourse().getCourseCover();
        this.courseDiyLabel=dto.getCourse().getDiyLabel();
        this.courseId=dto.getCourse().getCourseId();
        this.courseIntro=dto.getCourse().getCourseIntro();
        this.courseLabelList=dto.getCourse().getLabelList();
        this.courseLevelId=dto.getCourse().getLevelId();
        this.courseTitle=dto.getCourse().getCourseTitle();
        this.courseUpdateTime=dto.getCourse().getUpdateTime();
        this.userId=dto.getUser().getUserId();
        this.userNickName=dto.getUser().getNickName();
        this.userPic=dto.getUser().getUserPic();
    }

    public String getCourseDiyLabel() {
        return courseDiyLabel;
    }

    public List<Label> getCourseLabelList() {
        return courseLabelList;
    }

    public Date getCourseUpdateTime() {
        return courseUpdateTime;
    }

    public Byte getCourseLevelId() {

        return courseLevelId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseIntro() {

        return courseIntro;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getUserPic() {
        return userPic;
    }

    public Integer getUserId() {
        return userId;
    }
}
