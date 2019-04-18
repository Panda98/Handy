package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.handy.support.service.Course.ICourseService;

import java.util.List;


@RestController
public class CourseController {
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private Gson gson;

    /**
     * 获取热门推荐
     * @return json
     */
    @RequestMapping(value = "/main/banner", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    private String getBanner() {
        List<CourseSimpleVO> list = iCourseService.getBannerList();
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/recommend", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getRecommend(int uid){

        return null;
    }











}
