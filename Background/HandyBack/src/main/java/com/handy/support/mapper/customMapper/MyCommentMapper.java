package com.handy.support.mapper.customMapper;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import com.handy.support.pojo.comment.vo.ReplyBrief;
import com.handy.support.pojo.comment.vo.ReplyUserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyCommentMapper {
     List<Comment> getCommentsByCourseLimited(@Param("courseId") int courseId,@Param("startRow")  int startRow, @Param("size") int size);
     List<CommentDTO> getFullCommentsByCourseLimited(@Param("courseId") int courseId,@Param("startRow")  int startRow, @Param("size") int size);
     List<CommentDTO> getFullCommentsCourseMessageLimited(@Param("userId") int userId,@Param("startRow")  int startRow, @Param("size") int size);
     List<CommentReply> getCommentReplyByCommentIdLimited(@Param("commentId") int commentId, @Param("startRow")  int startRow, @Param("size") int size);
     List<ReplyUserVO> getCommentsReplyUserLimited(@Param("commentId") int commentId, @Param("startRow")  int startRow, @Param("size") int size);
     List<ReplyDTO> getMessageCommentsReplyUserLimited(@Param("userId") int userId,@Param("startRow")  int startRow, @Param("size") int size);
     List<ReplyDTO> getMessageReplyReplyUserLimited(@Param("userId") int userId,@Param("startRow")  int startRow, @Param("size") int size);
}
