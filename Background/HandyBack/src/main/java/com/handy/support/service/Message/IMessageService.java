package com.handy.support.service.Message;

import com.handy.support.pojo.comment.dto.CommentDTO;

import java.util.List;

public interface IMessageService {
    List<CommentDTO> getCourseCommentMessage(int uid, int page_no, int n);
}
