package com.handy.support.service.Follow;

import com.handy.support.entity.Follow;
import com.handy.support.entity.FollowExample;
import com.handy.support.mapper.FollowMapper;
import com.handy.support.mapper.UserMapper;
import com.handy.support.mapper.customMapper.MyFollowMapper;
import com.handy.support.mapper.customMapper.MyUsersCoursesMapper;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import com.handy.support.pojo.Follow.dto.FollowUserInfo;
import com.handy.support.pojo.UserCourse.dto.UsersCoursesBrief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements IFollowService{
    @Autowired
    FollowMapper followMapper;
    @Autowired
    MyFollowMapper myFollowMapper;
    @Autowired
    MyUsersCoursesMapper usersCoursesMapper;
    @Autowired
    UserMapper userMapper;
    public List<Follow> getFollows(int uid,int page_no,int n){
      return  myFollowMapper.getFollowsByUidLimited(uid,page_no,n);
    }
    public List<FollowUserInfo> getFollowUsersInfo(int uid, int page_no,int n){
        return myFollowMapper.getFollowsUserInfoListLimited(uid,page_no,n);
    }
    public boolean followOther(FollowDTO followDTO){
        if(hasFollowedSomeone(followDTO.getFollow().getFollowerId(),followDTO.getFollow().getUserId()))
            return false;
        if(userMapper.selectByPrimaryKey(followDTO.getFollow().getUserId())==null)
            return false;
                followMapper.insertSelective(followDTO.getFollow());
            return true;
    }
    public boolean unFollowOther(FollowDTO followDTO){
        //未关注该用户
        /*if(hasFollowedSomeone(followDTO.getFollow().getFollowerId(),followDTO.getFollow().getUserId()))
            return false;*/
            myFollowMapper.unFollowOther(followDTO.getFollow().getFollowerId(),followDTO.getFollow().getUserId());
        return true;
        //follower为当前用户，userId为其关注的人
       // myFollowMapper.unFollowOther(followDTO.getFollow().getFollowerId(),followDTO.getFollow().getUserId());
    }
    public List<UsersCoursesBrief> getFollowsUpdate(List<Integer>list,int page_no,int n){
       return usersCoursesMapper.getUsersCoursesBrief(list,page_no,n);
    }
    public List<Follow> getFollowsAll(int uid){
        FollowExample example=new FollowExample();
        FollowExample.Criteria criteria=example.createCriteria();
        criteria.andFollowerIdEqualTo(uid);
        return followMapper.selectByExample(example);
    }
    public boolean hasFollowedSomeone(int uid,int followId){
        Follow follow=myFollowMapper.hasFollowed(uid,followId);
        if(follow==null)
            return false;
        else
            return true;
    }
    public int getFansNum(int uid){
       return myFollowMapper.getFansNum(uid);
    }
    public int getFollowNum(int uid){
        return myFollowMapper.getFollowNum(uid);
    }
}
