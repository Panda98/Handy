package com.handy.support.service.Course;

import com.handy.support.entity.*;
import com.handy.support.pojo.course.dto.*;
import com.handy.support.pojo.course.vo.*;

import java.util.List;
/**
 * Created by joanie on 2019/4/11.
 */
public interface ICourseService {
    Course getCourseByID(Integer courseId);

    List<Label> getLabelList(Integer courseId);

    List<Item> getItemList(Integer courseId);

    List<Step> getStepList(Integer courseId);

    User getAuthor(Integer userId);

    List<Hot> getHotList(Integer n);

    List<CourseSimpleVO> getBannerList();

    List<CourseSimpleVO> getRecommendList(Integer userId, Integer page_no, Integer n);

    CourseDetailVO getCourseDetail(Integer courseId);

    Integer collect(Integer CourseId,Integer albumId);

    Integer uncollect(Integer CourseId,Integer albumId);

    Integer likeCourse(Integer userId, Integer courseId);

    Integer unlikeCourse(Integer userId, Integer courseId);

    Integer isCollected(Integer userId, Integer courseId);

    Integer isLiked(Integer userId, Integer courseId);

    List<CourseSimpleVO> getCollectedCourse(Integer albumId);

    List<CourseSimpleVO> getMyCourse(Integer userId,Integer page_no,Integer n);

    Integer publishCourse(CourseEditDTO e);

}
