package com.handy.support.pojo.Message.dto;

import com.handy.support.pojo.Follow.dto.CourseBrief;
import com.handy.support.pojo.Follow.dto.UserBrief;

public class FavorDTO extends FavorBrief{
    private CourseBrief course;
    private UserBrief user;

    public void setUser(UserBrief user) {
        this.user = user;
    }

    public UserBrief getUser() {
        return user;
    }

    public void setCourse(CourseBrief course) {
        this.course = course;
    }

    public CourseBrief getCourse() {
        return course;
    }
}
