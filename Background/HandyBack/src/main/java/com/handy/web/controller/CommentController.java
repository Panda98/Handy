package com.handy.web.controller;

import com.handy.support.pojo.comment.vo.ComPush;
import com.handy.support.pojo.comment.vo.ComRepReq;
import com.handy.support.pojo.comment.vo.CourseComReq;
import com.handy.support.pojo.comment.vo.RepComReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="/comment",produces = "application/json; charset=utf-8")
public class CommentController {
    @RequestMapping(value="/all",method = GET)
   public String getCourseComment(CourseComReq req){
return null;
   }
    @RequestMapping(value="/replyComment",method = GET)
    public String getCommentReply(ComRepReq req){
        return null;
    }
    @RequestMapping(value="/push",method = RequestMethod.POST)
    public String pushComment(ComPush req){
        return null;
    }
    @RequestMapping(value="/reply",method = GET)
    public String replyComment(RepComReq req){
        return null;
    }
}

