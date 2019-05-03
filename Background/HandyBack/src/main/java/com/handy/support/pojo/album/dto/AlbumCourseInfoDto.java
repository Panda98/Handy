package com.handy.support.pojo.album.dto;

import com.handy.support.entity.Label;
import com.handy.support.pojo.album.vo.AlbumCourseInfoVO;

import java.util.List;

/**
 * Created by Pan on 2019/4/15.
 */
public class AlbumCourseInfoDto {
    private int courseId;
    private String courseTitle;
    private String courseIntro;
    private String courseCover;
    private String userNickname;
    private int userId;
    private int levelId;
    private List<Label> labelList;
    private String diyLabel;

    public AlbumCourseInfoDto(){
        super();
    }

    public AlbumCourseInfoDto(int courseId, String courseTitle, String courseIntro, String userNickname, int userId) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseIntro = courseIntro;
        this.userNickname = userNickname;
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
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

    public AlbumCourseInfoVO revert2VO(){
        AlbumCourseInfoVO vo = new AlbumCourseInfoVO();
        vo.setCourseId(this.getCourseId());
        vo.setAuthorName(this.getUserNickname());
        vo.setCourseIntro(this.getCourseIntro());
        vo.setCourseTitle(this.getCourseTitle());
        vo.setCourseCover(this.getCourseCover());
        vo.setLabelList(this.labelList);
        vo.setDiyLabel(this.diyLabel);
        return vo;
    }
}
