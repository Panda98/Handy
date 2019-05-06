package com.example.handy.core.bean;

import java.util.List;

public class CommentMessageData {

    private int commentId;
    private String commentContent;
    private String commentUpdateTime;
    private int userId;
    private String userNickName;
    private String userPic;
    private int courseId;
    private String courseTitle;
    private String courseCover;
    private String courseDiyLabel;
    private String courseUpdateTime;
    private int courseLevelId;
    private String courseIntro;
    private List<LabelData> courseLabelList;

    public int getCommentId() {
        return commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public String getCommentUpdateTime() {
        return commentUpdateTime;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getUserPic() {
        return userPic;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public String getCourseDiyLabel() {
        return courseDiyLabel;
    }

    public String getCourseUpdateTime() {
        return courseUpdateTime;
    }

    public int getCourseLevelId() {
        return courseLevelId;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public List<LabelData> getCourseLabelList() {
        return courseLabelList;
    }
}
