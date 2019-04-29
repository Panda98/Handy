package com.handy.support.pojo.UserCourse.dto;

import com.handy.support.pojo.Follow.dto.CourseBrief;
import com.handy.support.pojo.Follow.dto.UserBrief;

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
