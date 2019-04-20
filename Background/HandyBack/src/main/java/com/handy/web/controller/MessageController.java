package com.handy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/message",produces = "application/json; charset=utf-8")
public class MessageController {
    @RequestMapping(value = "/courseComment",method = GET)
    public String getMessageComment(int uid,int page_no,int n){
return null;
    }
    @RequestMapping(value = "/commentReply",method = GET)
    public String getMessageCommentReply(int uid,int page_no,int n){
return null;
    }
    @RequestMapping(value = "/courseLike",method = GET)
    public String getMessageCourseComment(int uid,int page_no,int n){
return null;
    }
}
