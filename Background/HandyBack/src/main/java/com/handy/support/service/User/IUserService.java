package com.handy.support.service.User;

import com.handy.support.entity.User;

/**
 * Created by Pan on 2019/4/10.
 */
public interface IUserService {
    void addUser(String email,String psw);
    User getUserByID(String id);
}
