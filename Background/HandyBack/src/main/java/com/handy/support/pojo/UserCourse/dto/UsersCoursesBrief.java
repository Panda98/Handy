package com.handy.support.pojo.UserCourse.dto;

import com.handy.support.entity.Course;
import com.handy.support.entity.User;
import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.Follow.vo.UserBrief;

public class UsersCoursesBrief {
    private UserBrief user;
    private CourseBrief course;
public UsersCoursesBrief(){
    user=new UserBrief();
    course=new CourseBrief();
}
    public void setUser(UserBrief user) {
        this.user = user;
    }

    public UserBrief getUser() {
        return user;
    }

    public CourseBrief getCourse() {
        return course;
    }

    public void setCourse(CourseBrief course) {
        this.course = course;
    }
}
