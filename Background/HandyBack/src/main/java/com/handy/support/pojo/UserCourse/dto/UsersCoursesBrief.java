package com.handy.support.pojo.UserCourse.dto;

import com.handy.support.entity.Course;
import com.handy.support.entity.User;
import com.handy.support.pojo.Follow.vo.CourseBrief;
import com.handy.support.pojo.Follow.vo.UserBrief;

public class UsersCoursesBrief extends CourseBrief{
    private UserBrief user;
public UsersCoursesBrief(){
}
    public void setUser(UserBrief user) {
        this.user = user;
    }

    public UserBrief getUser() {
        return user;
    }
}
