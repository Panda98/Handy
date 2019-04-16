package com.handy.support.mapper;

import com.handy.support.entity.AlbumCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Pan on 2019/4/15.
 */
public interface MyAlbumCoursesMapper {
    List<AlbumCourse> getCoursesByAlbumLimited(@Param("albumId") int albumId, @Param("startRow")  int startRow, @Param("size") int size);
}
