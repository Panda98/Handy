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
     public List<Comment> getCourseComment(CourseComReq req);
     public List<CommentDTO> getFullCourseComment(CourseComReq req);
     public List<ReplyUserDTO> getCommentReplyUserLimited(ComRepReq req);
     public void pushCommentToCourse(CommentPush req);
     public List<CommentReply> getCommentReply(ComRepReq req) ;
     public void pushCommentReply(ReplyPush req);
}
