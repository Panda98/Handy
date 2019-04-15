package com.handy.support.pojo.Follow.dto;


import com.handy.support.entity.Follow;
import com.handy.support.pojo.Follow.vo.FollowVO;

public class FollowDTO {
    private Follow follow;
    public FollowDTO(){
        follow=new Follow();
    }
    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
    public void setFollow(FollowVO follow) {
        this.follow.setUserId(follow.getFollow_id());
        this.follow.setFollowerId(follow.getUid());
    }
}
