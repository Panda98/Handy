package com.handy.support.service.User;

import com.handy.support.entity.User;
import com.handy.support.pojo.dto.UserDto;
import com.handy.support.pojo.vo.UserVO;

import java.sql.SQLException;

/**
 * Created by Pan on 2019/4/10.
 */
public interface IUserService {
    String addUser(String email,String psw);
    UserDto getUserByID(String id);
    User getUserByEmail(String email);

    UserVO revert2VO(User user);
}
