package com.handy.support.pojo.Message.vo;

import com.handy.support.entity.Label;
import com.handy.support.pojo.comment.dto.CommentDTO;

import java.util.Date;
import java.util.List;

public class CourseMessageVO {
    private Integer commentId;
    private String commentContent;
    private Date commentUpdateTime;
    private int userId;
    private String userNickName;
    private String userPic;
    private int courseId;
    private  String courseTitle;
    private String courseCover;
    private String courseDiyLabel;
    private Date courseUpdateTime;
    private Byte courseLevelId;
    private String courseIntro;
    private List<Label> courseLabelList;
    public CourseMessageVO(CommentDTO dto){
        this.commentContent=dto.getCommentContent();
        this.commentId=dto.getCommentId();
        this.commentUpdateTime=dto.getUpdateTime();
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

    public Integer getCommentId() {
        return commentId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Date getCommentUpdateTime() {
        return commentUpdateTime;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public Byte getCourseLevelId() {
        return courseLevelId;
    }

    public Date getCourseUpdateTime() {
        return courseUpdateTime;
    }

    public List<Label> getCourseLabelList() {
        return courseLabelList;
    }

    public String getCourseDiyLabel() {
        return courseDiyLabel;
    }
}
