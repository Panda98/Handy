package com.handy.support.service.Course;

import com.handy.support.entity.Course;
import com.handy.support.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by joanie on 2019/4/11.
 */
@Service("courseService")
public class CourseServiceImpl implements ICourseSevice  {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseMapper courseMapper;

    public Course getCourseByID(String courseId){
        return courseMapper.selectByPrimaryKey(Integer.parseInt(courseId));
    }
}
