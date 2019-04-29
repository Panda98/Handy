package com.handy.support.pojo.UserCourse.vo;

import com.handy.support.entity.Label;
import com.handy.support.pojo.UserCourse.dto.UsersCoursesBrief;

import java.util.Date;
import java.util.List;

public class UserCourseUpdate {
  /*  private int userId;
    private String nickName;
    private String userPic;
    private int courseId;
    private  String courseTitle;
    private String courseCover;
    private String diyLabel;
    private Date updateTime;
    private Byte levelId;*/
   private int courseId;
   private String courseTitle;
   private String courseCover;
   private String courseIntro;
   private List<Label> labelList;
   private String diyLabel;
   private int  authorId;
   private String  authorName;
   private String  authorImage;
   private Date publishTime;
    public UserCourseUpdate(UsersCoursesBrief dto){
        this.authorId=dto.getUser().getUserId();
        this.authorImage=dto.getUser().getUserPic();
        this.authorName=dto.getUser().getNickName();
        this.courseCover=dto.getCourseCover();
        this.courseId=dto.getCourseId();
        this.courseIntro=dto.getCourseIntro();
        this.courseTitle=dto.getCourseTitle();
        this.diyLabel=dto.getDiyLabel();
        this.labelList=dto.getLabelList();
        this.publishTime=dto.getUpdateTime();
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

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setDiyLabel(String diyLabel) {
        this.diyLabel = diyLabel;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public int getAuthorId() {
        return authorId;
    }

    public List<Label> getLabelList() {
        return labelList;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
