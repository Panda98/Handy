package com.handy.web.controller;

import com.handy.support.pojo.comment.dto.CommentDTO;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.dto.ReplyPush;
import com.handy.support.pojo.comment.dto.ReplyUserDTO;
import com.handy.support.pojo.comment.vo.*;
import com.handy.support.service.Comment.CommentServiceImpl;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value="/comment",produces = "application/json; charset=utf-8")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @RequestMapping(value="/all",produces = "application/json; charset=utf-8",method = GET)
   public String getCourseComment( CourseComReq req){
        List<CommentDTO>comments=commentService.getFullCourseComment(req);
        List<CourseCommentVO>list=new ArrayList<CourseCommentVO>();
        for(int i=0;i<comments.size();i++){
            list.add(new CourseCommentVO(comments.get(i)));
        }
        ReturnCode<List<CourseCommentVO>> code = new ReturnCode<List<CourseCommentVO>>(list);
        return code.returnHandler();
   }
    @RequestMapping(value="/replyComment",produces = "application/json; charset=utf-8",method = GET)
    public String getCommentReply(ComRepReq req){
        List<ReplyUserDTO>commentReplies=commentService.getCommentReplyUserLimited(req);
        List<ReplyUserVO>list=new ArrayList<ReplyUserVO>();
        for(int i=0;i<commentReplies.size();i++){
            list.add(new ReplyUserVO(commentReplies.get(i)));
        }
        ReturnCode<List> code = new ReturnCode<List>(list);
        return code.returnHandler();
    }
    @RequestMapping(value="/push",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String pushComment(@RequestBody  ComPush req){
        ErrorEnum error=ErrorEnum.SUCCESS;
        CommentPush commentPush=new CommentPush();
        commentPush.setComment(req);
        boolean result=commentService.pushCommentToCourse(commentPush);
        if(result==false)
            error=ErrorEnum.PUSHCOMMENT_FAIL;
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(error,result);
        return code.returnHandler();
    }
    @RequestMapping(value="/reply",produces = "application/json; charset=utf-8",method = POST)
    public String replyComment(@RequestBody  RepComReq req){
        ErrorEnum error=ErrorEnum.SUCCESS;
        ReplyPush replyPush=new ReplyPush();
        replyPush.setReply(req);
        boolean result=commentService.pushCommentReply(replyPush);
        if(result==false)
            error=ErrorEnum.REPLY_FAIL;
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(error,result);
        return code.returnHandler();
    }
}

