package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.entity.User;
import com.handy.support.pojo.dto.UserDto;
import com.handy.support.pojo.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value="/user/login",produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String login(String username,String password){
        User user = iUserService.getUserByEmail(username);
        String msg = "";
        if(user == null){
            msg = "用户不存在！";
        }
        else if(!user.getLoginPassword().equals(password)){
            msg = "密码错误！";
        }
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        if(msg.equals(""))
            return gson.toJson(new UserVO(dto));
        return msg;
    }
}
