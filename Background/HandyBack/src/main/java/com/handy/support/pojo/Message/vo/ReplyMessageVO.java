package com.handy.support.pojo.Message.vo;

import com.handy.support.entity.Label;
import com.handy.support.pojo.Follow.dto.CourseBrief;
import com.handy.support.pojo.Follow.dto.UserBrief;
import com.handy.support.pojo.comment.dto.CommentBrief;
import com.handy.support.pojo.comment.dto.ReplyBrief;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import com.handy.support.pojo.comment.vo.ReplyUserVO;

import java.util.Date;
import java.util.List;

public class ReplyMessageVO extends ReplyUserVO implements Comparable<ReplyMessageVO>{
    private int inCourseId;
    private  String inCourseTitle;
    private String inCourseCover;
    private String inCourseDiyLabel;
    private Date inCourseUpdateTime;
    private Byte inCourseLevelId;
    private String inCourseIntro;
    private List<Label> inCourseLabelList;

    private Integer inCommentId;
    private String inCommentContent;
    private Integer inCommentUserId;
    private Date inCommentUpdateTime;

    private String toReplyContent;
    private Date toReplyUpdateTime;

    private int userId;
    private String userPic;
    private String userNickName;

    public String getUserPic() {
        return userPic;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public ReplyMessageVO(ReplyDTO dto, int type){
        super(dto,type);
        this.inCommentContent=dto.getComment().getCommentContent();
        this.inCommentId=dto.getComment().getCommentId();
        this.inCommentUpdateTime=dto.getComment().getUpdateTime();
        this.inCommentUserId=dto.getComment().getUserId();
        this.inCourseCover=dto.getCourse().getCourseCover();
        this.inCourseDiyLabel=dto.getCourse().getDiyLabel();
        this.inCourseId=dto.getCourse().getCourseId();
        this.inCourseIntro=dto.getCourse().getCourseIntro();
        this.inCourseLabelList=dto.getCourse().getLabelList();
        this.inCourseLevelId=dto.getCourse().getLevelId();
        this.inCourseTitle=dto.getCourse().getCourseTitle();
        this.inCourseUpdateTime=dto.getCourse().getUpdateTime();
        this.toReplyContent=dto.getReplyContent();
        this.toReplyUpdateTime=dto.getUpdateTime();
        this.userId=dto.getUser().getUserId();
        this.userNickName=dto.getUser().getNickName();
        this.userPic=dto.getUser().getUserPic();
    }

    public Byte getInCourseLevelId() {
        return inCourseLevelId;
    }

    public Date getInCommentUpdateTime() {
        return inCommentUpdateTime;
    }

    public Date getInCourseUpdateTime() {
        return inCourseUpdateTime;
    }

    public int getInCourseId() {
        return inCourseId;
    }

    public Integer getInCommentId() {
        return inCommentId;
    }

    public Integer getInCommentUserId() {
        return inCommentUserId;
    }

    public List<Label> getInCourseLabelList() {
        return inCourseLabelList;
    }

    public String getInCommentContent() {
        return inCommentContent;
    }

    public Date getToReplyUpdateTime() {
        return toReplyUpdateTime;
    }

    public String getInCourseCover() {
        return inCourseCover;
    }

    public String getInCourseDiyLabel() {
        return inCourseDiyLabel;
    }

    public String getInCourseIntro() {
        return inCourseIntro;
    }

    public String getInCourseTitle() {
        return inCourseTitle;
    }


    public String getToReplyContent() {
        return toReplyContent;
    }

    public int compareTo(ReplyMessageVO o) {
        if(o.getUpdateTime().after(this.getUpdateTime()))
            return -1;
        else if(o.getUpdateTime().before(this.getUpdateTime()))
            return 1;
        else
            return 0;
    }
}
