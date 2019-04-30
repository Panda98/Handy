package com.handy.web.controller;

import com.handy.support.entity.Follow;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import com.handy.support.pojo.Follow.dto.FollowUserInfo;
import com.handy.support.pojo.Follow.vo.FollowUsersVO;
import com.handy.support.pojo.Follow.vo.FollowVO;
import com.handy.support.pojo.UserCourse.dto.UsersCoursesBrief;
import com.handy.support.pojo.UserCourse.vo.UserCourseUpdate;
import com.handy.support.service.Follow.FollowServiceImpl;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value="/follow",produces = "application/json; charset=utf-8")
public class FollowController {
    @Autowired
    FollowServiceImpl followService;
    @RequestMapping(value="/follows",method = RequestMethod.GET)
    public String getFollowers(int uid,int page_no,int n){
       /* List<Follow> followList=followService.getFollows(uid,page_no,n);
        List<FollowVO>followVOS=new LinkedList<FollowVO>();
        for(int i=0;i<followList.size();i++){
            followVOS.add(new FollowVO(new FollowDTO(followList.get(i))));
        }*/
       List<FollowUserInfo> list=followService.getFollowUsersInfo(uid,page_no,n);
       List<FollowUsersVO>follow=new ArrayList<FollowUsersVO>();
       for(int i=0;i<list.size();i++){
           follow.add(new FollowUsersVO(list.get(i)));
       }
        ReturnCode<List> code = new ReturnCode<List>(follow);
        return code.returnHandler();
    }
    @RequestMapping(value="/follow",method = RequestMethod.GET)
    public String followSomeone(FollowVO follow){
        FollowDTO followPush=new FollowDTO();
        followPush.setFollow(follow);
        followService.followOther(followPush);
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(true);
        return code.returnHandler();
    }
    @RequestMapping(value="/unfollow",method = RequestMethod.GET)
    public String unFollowSomeone(FollowVO followVOo){
        FollowDTO followDTO=new FollowDTO();
        followDTO.setFollow(followVOo);
        followService.unFollowOther(followDTO);
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(true);
        return code.returnHandler();
    }
    @RequestMapping(value="/moments",method = RequestMethod.GET)
    public String followsMoment(int uid,int page_no,int n){
       // List<Follow> followList=followService.getFollows(uid,page_no,n);
        List<Follow> followList=followService.getFollowsAll(uid);
        List<Integer>follows=new LinkedList<Integer>();
        for(int i=0;i<followList.size();i++){
            follows.add(new Integer(followList.get(i).getUserId()));
        }
        List<UsersCoursesBrief>list=followService.getFollowsUpdate(follows,page_no,n);
        List<UserCourseUpdate>update=new ArrayList<UserCourseUpdate>();
        for(int i=0;i<list.size();i++){
            update.add(new UserCourseUpdate(list.get(i)));
        }
        ReturnCode<List> code = new ReturnCode<List>(update);
        return code.returnHandler();
    }
}
