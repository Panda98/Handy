package com.example.handy.core.bean;

import java.util.List;

public class LikeMessageData {
    private int userId;
    private int courseId;
    private String courseTitle;
    private String courseCover;
    private String courseDiyLabel;
    private String courseUpdateTime;
    private int courseLevelId;
    private String courseIntro;
    private List<LabelData> courseLabelList;
    private String userNickName;
    private String userPic;

    public int getUserId() {
        return userId;
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

    public String getUserNickName() {
        return userNickName;
    }

    public String getUserPic() {
        return userPic;
    }
}
