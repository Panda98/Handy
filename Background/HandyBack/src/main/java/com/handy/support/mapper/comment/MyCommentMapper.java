package com.handy.support.mapper.comment;

import com.handy.support.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCommentMapper {
     List<Comment> getCommentsByCourseLimited(@Param("courseId") int courseId,@Param("startRow")  int startRow, @Param("size") int size);
}
