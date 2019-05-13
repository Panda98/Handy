package com.handy.support.service.Follow;

import com.handy.support.entity.Follow;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import com.handy.support.pojo.Follow.dto.FollowUserInfo;
import com.handy.support.pojo.UserCourse.dto.UsersCoursesBrief;

import java.util.List;

public interface IFollowService {
    List<Follow> getFollows(int uid, int page_no, int n);
    List<FollowUserInfo> getFollowUsersInfo(int uid, int page_no, int n);
    boolean followOther(FollowDTO followDTO);
    List<UsersCoursesBrief> getFollowsUpdate(List<Integer>list, int page_no, int n);
    boolean unFollowOther(FollowDTO followDTO);
    boolean hasFollowedSomeone(int uid,int followId);
    List<Follow> getFollowsAll(int uid);
    int getFollowNum(int uid);
    public int getFansNum(int uid);
}
