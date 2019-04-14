package com.handy.web.controller;

import com.handy.support.entity.Comment;
import com.handy.support.entity.CommentReply;
import com.handy.support.pojo.comment.dto.CommentPush;
import com.handy.support.pojo.comment.vo.ComPush;
import com.handy.support.pojo.comment.vo.ComRepReq;
import com.handy.support.pojo.comment.vo.CourseComReq;
import com.handy.support.pojo.comment.vo.RepComReq;
import com.handy.support.pojo.user.vo.UserVO;
import com.handy.support.service.Comment.CommentServiceImpl;
import com.handy.support.utils.status.ReturnCode;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="/comment",produces = "application/json; charset=utf-8")
public class CommentController {
    @Autowired
    CommentServiceImpl commentService;
    @RequestMapping(value="/all",method = GET)
   public String getCourseComment( CourseComReq req){
        List<Comment>comments=commentService.getCourseComment(req);
        ReturnCode<List> code = new ReturnCode<List>(comments);
        return code.returnHandler();
   }
    @RequestMapping(value="/replyComment",method = GET)
    public String getCommentReply(ComRepReq req){
        List<CommentReply>commentReplies=commentService.getCommentReply(req);
        ReturnCode<List> code = new ReturnCode<List>(commentReplies);
        return code.returnHandler();
    }
    @RequestMapping(value="/push",method = RequestMethod.POST)
    public String pushComment(ComPush req){
        CommentPush commentPush=new CommentPush();
        commentPush.setComment(req);
        commentService.pushCommentToCourse(commentPush);
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(true);
        return code.returnHandler();
    }
    @RequestMapping(value="/reply",method = GET)
    public String replyComment(RepComReq req){
        ReplyPush replyPush=new CommentPush();
        commentPush.setComment(req);
        commentService.pushCommentToCourse(commentPush);
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(true);
        return code.returnHandler();
    }
}

