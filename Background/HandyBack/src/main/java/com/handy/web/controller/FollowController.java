package com.handy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/follow",produces = "application/json; charset=utf-8")
public class FollowController {
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String getfollowers(int uid){

    }

}
