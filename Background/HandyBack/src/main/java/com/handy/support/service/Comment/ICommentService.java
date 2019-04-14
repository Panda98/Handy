package com.handy.support.service.Comment;

import com.handy.support.entity.Comment;

import java.util.List;

public interface ICommentService {
     List<Comment> getCourseComment(int course_id, int from, int to);

}
