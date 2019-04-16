package com.handy.support.mapper;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.entity.Follow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyFollowMapper {
    List<Follow> getFollowsByUidLimited(@Param("userId") int userId, @Param("startRow")  int startRow, @Param("size") int size);
   void unFollowOther(@Param("userId") int userId,@Param("followId") int followId);
    // List<CommentReply> getCommentReplyByCommentIdLimited(@Param("commentId") int commentId, @Param("startRow")
}
