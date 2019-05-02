package com.handy.support.service.Message;

import com.handy.support.mapper.customMapper.MyCommentMapper;
import com.handy.support.mapper.customMapper.MyMessageMapper;
import com.handy.support.pojo.Message.dto.FavorDTO;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService{
    @Autowired
    private MyCommentMapper commentMapper;
    @Autowired
    private MyMessageMapper messageMapper;
    public List<CommentDTO> getCourseCommentMessage(int uid,int page_no,int n){
        return commentMapper.getFullCommentsCourseMessageLimited(uid,page_no,n);
    }
    public List<ReplyDTO> getCommentReplyMessage(int uid,int page_no,int n){
        return commentMapper.getMessageCommentsReplyUserLimited(uid,page_no,n);
    }
    public List<ReplyDTO> getReplyReplyMessage(int uid,int page_no,int n){
        return commentMapper.getMessageReplyReplyUserLimited(uid,page_no,n);
    }
    public List<FavorDTO> getFavorMessage(int uid, int page_no, int n){
        return messageMapper.getFavorMessage(uid,page_no,n);
    }
}
