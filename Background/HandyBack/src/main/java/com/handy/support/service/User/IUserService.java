package com.handy.support.service.User;

import com.handy.support.entity.User;
import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.pojo.user.vo.UserVO;
import com.handy.support.utils.status.ErrorEnum;

import java.util.List;

/**
 * Created by Pan on 2019/4/10.
 */
public interface IUserService {
    ErrorEnum addUser(String email, String psw);
    User getUserByEmail(String email);
    UserDto getUserByID(int id);

    ErrorEnum updateUser(UserDto vo);

    ErrorEnum addUserLabels(int uid, List<Integer>labels);

    int getFanCount(int uid);


    UserVO revert2VO(User user);
}
