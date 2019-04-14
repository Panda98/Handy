package com.handy.support.service.Follow;

import com.handy.support.entity.Follow;
import com.handy.support.mapper.FollowMapper;
import com.handy.support.mapper.customMapper.MyFollowMapper;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements IFollowService{
    @Autowired
    FollowMapper followMapper;
    @Autowired
    MyFollowMapper myFollowMapper;
    public List<Follow> getFollows(int uid,int page_no,int n){
      return  myFollowMapper.getFollowsByUidLimited(uid,page_no,n);
    }
    public void followOther(FollowDTO followDTO){
        followMapper.insertSelective(followDTO.getFollow());
    }
    public void unFollowOther(FollowDTO followDTO){
        //follower为当前用户，userId为其关注的人
        myFollowMapper.unFollowOther(followDTO.getFollow().getFollowerId(),followDTO.getFollow().getUserId());
    }
}
