package com.example.handy.core.bean;

import java.util.List;

public class ReplyMessageData {

    //private int inCourseId;
    //    //private String inCourseTitle;
    //    //private String inCourseCover;
    //    //private String inCourseDiyLabel;
    //    //private String inCourseUpdateTime;
    //    //private int inCourseLevelId;
    //    //private String inCourseIntro;
    //    //private List<LabelData> inCourseLabelList;
    //    //private int inCommentId;
    //    //private String inCommentContent;
    //    //private int inCommentUserId;
    //    //private String inCommentUpdateTime;
    //    //private String toReplyContent;
    //    //private String toReplyUpdateTime;
    //    //private int userId;
    //    //private String
    //    //private int toReplyType;
    //    //private int replyId;
    //    //private String replyContent;
    //    //private int toReplyId;
    //    //private String updateTime;
    //    //private int toReplyUserId;
    //    //private String toReplyNickName;
    //    //private String toReplyUserPic;

    private int inCourseId;
    private  String inCourseTitle;
    private String inCourseCover;
    private String inCourseDiyLabel;
    private String inCourseUpdateTime;
    private Byte inCourseLevelId;
    private String inCourseIntro;
    private List<LabelData> inCourseLabelList;

    private int inCommentId;
    private String inCommentContent;
    private int inCommentUserId;
    private String inCommentUpdateTime;

    private String toReplyContent;
    private String toReplyUpdateTime;

    private int userId;
    private String userPic;
    private String userNickName;
    private int toReplyType;
    private int replyId;
    private String replyContent;
    private int toReplyId;//有可能是commentTable中的，有可能是replyTable中的，视type定
    private String updateTime;
    private int toReplyUserId;
    private String toReplyNickName;
    private String toReplyUserPic;

    public int getToReplyType() {
        return toReplyType;
    }

    public int getReplyId() {
        return replyId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public int getToReplyId() {
        return toReplyId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getToReplyUserId() {
        return toReplyUserId;
    }

    public String getToReplyNickName() {
        return toReplyNickName;
    }

    public String getToReplyUserPic() {
        return toReplyUserPic;
    }

    public int getInCourseId() {
        return inCourseId;
    }

    public String getInCourseTitle() {
        return inCourseTitle;
    }

    public String getInCourseCover() {
        return inCourseCover;
    }

    public String getInCourseDiyLabel() {
        return inCourseDiyLabel;
    }

    public String getInCourseUpdateTime() {
        return inCourseUpdateTime;
    }

    public Byte getInCourseLevelId() {
        return inCourseLevelId;
    }

    public String getInCourseIntro() {
        return inCourseIntro;
    }

    public List<LabelData> getInCourseLabelList() {
        return inCourseLabelList;
    }

    public int getInCommentId() {
        return inCommentId;
    }

    public String getInCommentContent() {
        return inCommentContent;
    }

    public int getInCommentUserId() {
        return inCommentUserId;
    }

    public String getInCommentUpdateTime() {
        return inCommentUpdateTime;
    }

    public String getToReplyContent() {
        return toReplyContent;
    }

    public String getToReplyUpdateTime() {
        return toReplyUpdateTime;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public String getUserNickName() {
        return userNickName;
    }
}
