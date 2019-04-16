package com.handy.support.mapper;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCommentMapper {
     List<Comment> getCommentsByCourseLimited(@Param("courseId") int courseId,@Param("startRow")  int startRow, @Param("size") int size);
     List<CommentReply> getCommentReplyByCommentIdLimited(@Param("commentId") int commentId, @Param("startRow")  int startRow, @Param("size") int size);
}
