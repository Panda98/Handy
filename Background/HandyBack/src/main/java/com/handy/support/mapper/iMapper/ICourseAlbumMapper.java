package com.handy.support.mapper.iMapper;

import com.handy.support.entity.AlbumCourse;
import com.handy.support.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * Created by joanie on 2019/4/16.
 */
public interface ICourseAlbumMapper {
    Integer deleteOne(@Param("albumId")Integer albumId,@Param("courseId")Integer courseId);

    Integer isCollected(@Param("userId")Integer userId, @Param("courseId")Integer courseId);

    List<Course> getCourseList(@Param("albumId")Integer albumId);
}
