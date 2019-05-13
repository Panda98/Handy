package com.handy.web.controller;

import com.handy.support.entity.Follow;
import com.handy.support.pojo.Follow.dto.FollowDTO;
import com.handy.support.pojo.Follow.dto.FollowUserInfo;
import com.handy.support.pojo.Follow.vo.FollowUsersVO;
import com.handy.support.pojo.Follow.vo.FollowVO;
import com.handy.support.pojo.UserCourse.dto.UsersCoursesBrief;
import com.handy.support.pojo.UserCourse.vo.UserCourseUpdate;
import com.handy.support.service.Follow.FollowServiceImpl;
import com.handy.support.service.Recommend.RecommendServiceImpl;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="/follow",produces = "application/json; charset=utf-8")
public class FollowController {
    @Autowired
    FollowServiceImpl followService;
    @Autowired
    RecommendServiceImpl recommendService;
    @RequestMapping(value="/follows",method = RequestMethod.GET)
    public String getFollows(int uid,int page_no,int n){
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
        ErrorEnum error=ErrorEnum.SUCCESS;
        FollowDTO followPush=new FollowDTO();
        followPush.setFollow(follow);
        boolean result=followService.followOther(followPush);
        if(result==false)
            error=ErrorEnum.FOLLOW_FAIL;
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(error,result);
        return code.returnHandler();
    }
    @RequestMapping(value="/unfollow",method = RequestMethod.GET)
    public String unFollowSomeone(FollowVO followVO){
        ErrorEnum error=ErrorEnum.SUCCESS;
        FollowDTO followDTO=new FollowDTO();
        followDTO.setFollow(followVO);
        boolean result=followService.unFollowOther(followDTO);
        if(result==false)
            error=ErrorEnum.UNFOLLOW_FAIL;
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(error,result);
        return code.returnHandler();
    }
    @RequestMapping(value="/hasFollowed",method = RequestMethod.GET)
    public String hasFollowedSomeone(int uid,int follow_id){
        boolean judge=followService.hasFollowedSomeone(uid,follow_id);
        ReturnCode<Boolean> code = new ReturnCode<Boolean>(judge);
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
    @RequestMapping(value = "/test",method = GET)
    public String getTest(int uid){
        ReturnCode<Integer> code = new ReturnCode<Integer>(followService.getFollowNum(uid));
        return code.returnHandler();
    }
}
