package com.handy.support.service.Message;

import com.handy.support.pojo.Message.dto.FavorDTO;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.ReplyDTO;

import java.util.List;

public interface IMessageService {
    List<CommentDTO> getCourseCommentMessage(int uid,int page_no,int n);
    List<ReplyDTO> getCommentReplyMessage(int uid, int page_no, int n);
    List<ReplyDTO> getReplyReplyMessage(int uid,int page_no,int n);
    List<FavorDTO> getFavorMessage(int uid, int page_no, int n);
}
