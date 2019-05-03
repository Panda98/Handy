package com.handy.support.pojo.album.vo;

import com.handy.support.entity.Label;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.user.dto.UserDto;

import java.util.List;

/**
 * Created by Pan on 2019/4/15.
 */
public class AlbumCourseInfoVO {
    private int courseId;
    private String courseTitle;
    private String courseIntro;
    private String authorName;
    private String courseCover;
    private int levelId;
    private List<Label> labelList;
    private String diyLabel;


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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
}
