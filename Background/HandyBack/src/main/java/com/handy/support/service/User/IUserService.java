package com.handy.support.service.User;

import com.handy.support.entity.User;
import com.handy.support.pojo.dto.UserDto;

/**
 * Created by Pan on 2019/4/10.
 */
public interface IUserService {
//    void addUser(String email,String psw);
    UserDto getUserByID(String id);
    User getUserByEmail(String email);
}
