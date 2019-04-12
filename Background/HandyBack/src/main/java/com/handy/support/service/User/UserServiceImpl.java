package com.handy.support.service.User;


import com.handy.support.entity.User;
import com.handy.support.entity.UserExample;
import com.handy.support.mapper.UserMapper;
import com.handy.support.pojo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    public UserDto getUserByID(String id){
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        return dto;
    }
    public User getUserByEmail(String email){
        //select * from user where email=
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        userExample.or(criteria);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() == 0)
            return null;
        return users.get(0);
    }
}
