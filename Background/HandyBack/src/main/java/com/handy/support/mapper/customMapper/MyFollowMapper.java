package com.handy.support.mapper.customMapper;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.entity.Follow;
import com.handy.support.pojo.Follow.dto.FollowUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyFollowMapper {
    List<Follow> getFollowsByUidLimited(@Param("userId") int userId, @Param("startRow")  int startRow, @Param("size") int size);
   void unFollowOther(@Param("userId") int userId,@Param("followId") int followId);
    List<FollowUserInfo> getFollowsUserInfoListLimited(@Param("userId") int userId, @Param("startRow")  int startRow, @Param("size") int size);
    Follow hasFollowed(@Param("userId")int userId ,@Param("followId") int followId);
    int getFansNum(@Param("userId")int userId);
    int getFollowNum(@Param("userId")int userId);
    // List<CommentReply> getCommentReplyByCommentIdLimited(@Param("commentId") int commentId, @Param("startRow")
}
