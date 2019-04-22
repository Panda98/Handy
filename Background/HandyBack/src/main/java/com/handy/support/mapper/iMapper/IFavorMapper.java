package com.handy.support.mapper.iMapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by joanie on 2019/4/16.
 */
public interface IFavorMapper {
    Integer isLiked(@Param("userId")Integer userId,@Param("courseId")Integer courseId);

    Integer deleteOne(@Param("userId")Integer userId,@Param("courseId")Integer courseId);
}
