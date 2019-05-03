package com.handy.support.service.User;


import com.handy.support.entity.User;
import com.handy.support.entity.UserExample;
import com.handy.support.entity.UserLabel;
import com.handy.support.entity.UserLabelExample;
import com.handy.support.mapper.UserLabelMapper;
import com.handy.support.mapper.UserMapper;
import com.handy.support.pojo.user.dto.UserDto;
import com.handy.support.pojo.user.vo.UserVO;
import com.handy.support.utils.status.ErrorEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements IUserService{
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserLabelMapper userLabelMapper;

    public ErrorEnum addUser(String username, String password){
        User user = new User(username,password);
        try{
            userMapper.insertSelective(user);
        }catch (Exception e){
            String cause = e.getCause().getMessage();
            if(cause.contains("Duplicate"))
                return ErrorEnum.EMAIL_FAIL;

        }
        return ErrorEnum.SUCCESS;
    }

    public UserDto getUserByID(int id){
        User user = userMapper.selectByPrimaryKey(id);
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

    public ErrorEnum updateUser(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        try{
            int i = userMapper.updateByPrimaryKeySelective(user);
            if(i != 1)
                return ErrorEnum.USER_NOT_EXIST;
        }catch (Exception e){
            return ErrorEnum.UPDATE_FAIL;
        }
        return ErrorEnum.SUCCESS;
    }

    public ErrorEnum addUserLabels(int uid,List<Integer> labels){
        for(Integer label:labels){
            UserLabel userLabel = new UserLabel(uid,label);
            try{
                userLabelMapper.insert(userLabel);
            }catch (Exception e){
                return ErrorEnum.UPDATE_FAIL;
            }
        }
        return ErrorEnum.SUCCESS;
    }

    public UserVO revert2VO(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user,dto);
        return new UserVO(dto);
    }

    public int getFanCount(int uid){
        return 0;
    }

}
