package com.handy.support.mapper;

import com.handy.support.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by joanie on 2019/4/15.
 */
public interface ICourseItemMapper {
    List<Item> listByCourseId(@Param("courseId") Integer courseId);
}
