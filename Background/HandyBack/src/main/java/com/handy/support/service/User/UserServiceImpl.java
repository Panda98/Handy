package com.handy.support.service.User;


import com.handy.support.entity.User;
import com.handy.support.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    public void addUser(String email,String psw){
        User user = new User(email,psw);
        userMapper.insert(user);
    }
    public User getUserByID(String id){
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        return user;
    }
}
