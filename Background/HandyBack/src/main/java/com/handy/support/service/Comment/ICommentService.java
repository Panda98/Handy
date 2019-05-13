package com.handy.support.service.Comment;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.dto.ReplyPush;
import com.handy.support.pojo.comment.dto.ReplyUserDTO;
import com.handy.support.pojo.comment.vo.ComPush;
import com.handy.support.pojo.comment.vo.ComRepReq;
import com.handy.support.pojo.comment.vo.CourseComReq;

import java.util.List;

public interface ICommentService {
      List<Comment> getCourseComment(CourseComReq req);
      List<CommentDTO> getFullCourseComment(CourseComReq req);
      List<ReplyUserDTO> getCommentReplyUserLimited(ComRepReq req);
      boolean pushCommentToCourse(CommentPush req);
      List<CommentReply> getCommentReply(ComRepReq req) ;
      boolean pushCommentReply(ReplyPush req);
}
