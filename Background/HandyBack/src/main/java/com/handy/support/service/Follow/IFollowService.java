package com.handy.support.service.Follow;

import com.handy.support.entity.Follow;
import com.handy.support.mapper.customMapper.MyFollowMapper;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IFollowService {
    List<Follow> getFollows(int uid, int page_no, int n);
    void unFollowOther(FollowDTO followDTO);
}
