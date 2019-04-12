package com.handy.support.service.User;


import com.handy.support.entity.User;
import com.handy.support.entity.UserExample;
import com.handy.support.mapper.UserMapper;
import com.handy.support.pojo.dto.UserDto;
import com.handy.support.pojo.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.DuplicateKeyException;
import java.sql.SQLException;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    public String addUser(String username,String password){
        User user = new User(username,password);
        try{
            userMapper.insertSelective(user);
        }catch (Exception e){
            String cause = e.getCause().getMessage();
            if(cause.contains("Duplicate"))
                return "该邮箱已被注册";

        }
        return "success";
    }

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

    public UserVO revert2VO(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        return new UserVO(dto);
    }
}
