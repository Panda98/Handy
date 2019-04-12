package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.handy.support.service.User.IUserService;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Pan on 2019/4/11.
 */
@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private Gson gson;

    @RequestMapping(value="/user/id",produces = "application/json; charset=utf-8")
    private String getUserByID(String userId, HttpServletResponse response){
        User user = iUserService.getUserByID(userId);

        if(user == null){
           response.setStatus(401);
           String info = "{\"info\": \"用户不存在\"}";
           return info;
        }
        return gson.toJson(user);
    }
}
