package com.handy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/comment",produces = "application/json; charset=utf-8")
public class CommentController {
    @RequestMapping(value="/all",method = RequestMethod.GET)
   public String getCourseComment(int course_id,int page_no,int n){
return null;
   }
    @RequestMapping(value="/reply",method = RequestMethod.GET)
    public String getCommentReply(int comment_id,int page_no,int n){
        return null;
    }
    @RequestMapping(value="/push",method = RequestMethod.POST)
    public String getCommentPush(int uid,int comment_id,int page_no,int n){
        return null;
    }
}
