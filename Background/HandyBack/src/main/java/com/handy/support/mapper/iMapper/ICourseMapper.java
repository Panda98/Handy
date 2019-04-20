package com.handy.support.mapper.iMapper;

import com.handy.support.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public interface ICourseMapper {
    List<Course> getAll(@Param("start") Integer start, @Param("n") Integer n);

    List<Integer> getMyCourse(@Param("userId")Integer userId,@Param("start") Integer start, @Param("n") Integer n);

    //Integer insertCourse(@Param("course")Course course);

    Integer getLastId();
}
