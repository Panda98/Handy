package com.handy.support.mapper.courseMapper;

import com.handy.support.entity.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public interface ICourseLabelMapper {
    List<Label> listByCourseId(@Param("courseId") Integer courseId);
}
