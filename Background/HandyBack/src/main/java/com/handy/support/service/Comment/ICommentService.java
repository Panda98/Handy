package com.handy.support.service.Comment;

import com.handy.support.entity.Comment;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.vo.ComPush;
import com.handy.support.pojo.comment.vo.CourseComReq;

import java.util.List;

public interface ICommentService {
     List<Comment> getCourseComment(CourseComReq req);
     void pushCommentToCourse(CommentPush req);

}
