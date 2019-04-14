package com.handy.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/comment",produces = "application/json; charset=utf-8")
public class CommentController {
   public String getCourseComment(int course_id,int from,int to){

   }
}
