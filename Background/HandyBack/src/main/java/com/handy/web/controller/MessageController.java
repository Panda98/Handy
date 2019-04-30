package com.handy.web.controller;

import com.handy.support.pojo.Message.dto.FavorDTO;
import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.ReplyDTO;
import com.handy.support.pojo.comment.vo.CourseCommentVO;
import com.handy.support.service.Comment.CommentServiceImpl;
import com.handy.support.service.Message.MessageServiceImpl;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/message",produces = "application/json; charset=utf-8")
public class MessageController {
    @Autowired
    MessageServiceImpl messageService;
        @RequestMapping(value = "/courseComment",method = GET)
    public String getMessageComment(int uid,int page_no,int n){
        List<CommentDTO> comments=messageService.getCourseCommentMessage(uid,page_no,n);
        ReturnCode<List> code = new ReturnCode<List>(comments);
        return code.returnHandler();
    }
    @RequestMapping(value = "/commentReply",method = GET)
    public String getMessageCommentReply(int uid,int page_no,int n){
            List<ReplyDTO>list=messageService.getCommentReplyMessage(uid,page_no,n);
            list.addAll(messageService.getReplyReplyMessage(uid,page_no,n));
        Collections.sort(list);
        ReturnCode<List> code = new ReturnCode<List>(list);
        return code.returnHandler();
    }
    @RequestMapping(value = "/courseLike",method = GET)
    public String getMessageCourseComment(int uid,int page_no,int n){
            List<FavorDTO> list=messageService.getFavorMessage(uid,page_no,n);
        ReturnCode<List> code = new ReturnCode<List>(list);
        return code.returnHandler();
    }
}
