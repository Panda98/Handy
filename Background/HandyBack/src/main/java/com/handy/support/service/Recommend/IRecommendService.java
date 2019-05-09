package com.handy.support.service.Recommend;

import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.pojo.recommend.dto.UserItemLike;

import java.util.Date;
import java.util.List;

public interface IRecommendService {
    void insertUserItemLike(UserItemLike itemLike);
    void updatePreference(int uid,int item,float value);
    void UserLikeItem(int uid,int item);
    void UserUlikeItem(int uid,int item);
    void UserLikeItem(int uid,int item,float value);
    void UserUlikeItem(int uid,int item,float value);
    void UserFollowSomeone(int uid,int follow);
    void UserUfollowSomeone(int uid,int follow);
    void UserlikeAlbum(int uid,int album);
    void UserUlikeAlbum(int uid,int album);
    List<UserItemLike> getUpdates(Date lastUpdate);
    List<CourseSimpleVO> getCourseList(List<Long>courseList);
}
