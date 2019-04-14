package com.handy.support.pojo.Follow.vo;


import org.springframework.stereotype.Component;

@Component
public class FollowVO {
    private int uid;
    private int follow_id;
    public FollowVO(){}
public FollowVO(int uid,int follow_id){
        this.uid=uid;
        this.follow_id=follow_id;
}
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFollow_id() {
        return follow_id;
    }
//uid为当前用户，follow_id为其关注的人

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }
}
