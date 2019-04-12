package com.handy.web.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.handy.support.service.Course.ICourseSevice;


@RestController
public class CourseController {
    @Autowired
    private ICourseSevice iCourseSevice;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/main/banner")
    private String getCourseByID(String courseId){
        return gson.toJson(iCourseSevice.getCourseByID(courseId));
    }
}
