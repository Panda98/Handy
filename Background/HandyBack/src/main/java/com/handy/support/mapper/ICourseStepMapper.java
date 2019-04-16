package com.handy.support.mapper;

import com.handy.support.entity.Step;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public interface ICourseStepMapper {
    List<Step> listByCourseId(@Param("courseId") Integer courseId);
}
