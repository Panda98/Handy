package com.handy.support.pojo.album.vo;

import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.user.dto.UserDto;

/**
 * Created by Pan on 2019/4/15.
 */
public class AlbumCourseInfoVO {
    private int courseId;
    private String courseTitle;
    private String courseIntro;
    private String coursePic;
    private String authorName;


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

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }
}
