package com.handy.support.mapper.courseMapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by joanie on 2019/4/16.
 */
public interface ILikeMapper {
    Integer isLiked(@Param("userId")Integer userId,@Param("courseId")Integer courseId);

    Integer deleteOne(@Param("userId")Integer userId,@Param("courseId")Integer courseId);
}
