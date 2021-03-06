package com.handy.support.service.Recommend;

import com.handy.support.entity.Course;
import com.handy.support.mapper.UserMapper;
import com.handy.support.mapper.customMapper.MyFollowMapper;
import com.handy.support.mapper.customMapper.MyRecommendMapper;
import com.handy.support.mapper.iMapper.ICourseMapper;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.pojo.recommend.dto.UserItemLike;
import com.handy.support.service.Course.CourseServiceImpl;
import com.handy.support.service.Course.ICourseService;
import com.handy.support.service.Follow.FollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecommendServiceImpl implements IRecommendService{
    @Autowired
    MyRecommendMapper recommendMapper;
    @Autowired
    MyFollowMapper followMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ICourseService courseService;
    public void insertUserItemLike(UserItemLike itemLike){
        recommendMapper.insertSelective(itemLike);
    }
    public void updatePreference(int uid,int item,float value){
        List<UserItemLike> temp= recommendMapper.selectRecord(uid,item);
        if(temp.size()>1){
            recommendMapper.deleteRecord(uid,item);
            temp=null;
        }
        UserItemLike like=new UserItemLike();
        like.setItemId(item);
        like.setUserId(uid);
        like.setUpdateTime(new Date());
        if(temp==null||temp.size()==0){
            like.setPreference(value);
            recommendMapper.insertSelective(like);
        }
        else{
            like.setPreference(value);
            recommendMapper.updateRecord(like);
        }
    }
    public void UserLikeItem(int uid,int item){
       List<UserItemLike> temp= recommendMapper.selectRecord(uid,item);
        if(temp.size()>1){
            recommendMapper.deleteRecord(uid,item);
            temp=null;
        }
        UserItemLike like=new UserItemLike();
        like.setItemId(item);
        like.setUserId(uid);
        like.setUpdateTime(new Date());
       if(temp==null||temp.size()==0){
           like.setPreference(1);
           recommendMapper.insertSelective(like);
       }
       else{
           like.setPreference(temp.get(0).getPreference()+1);
           recommendMapper.updateRecord(like);
       }
    }
    public void UserUlikeItem(int uid,int item){
        List<UserItemLike> temp= recommendMapper.selectRecord(uid,item);
        if(temp.size()>1){
            recommendMapper.deleteRecord(uid,item);
            temp=null;
        }
        if(temp!=null&&temp.size()!=0){
            UserItemLike like=new UserItemLike();
            like.setItemId(item);
            like.setUserId(uid);
            like.setPreference(temp.get(0).getPreference()-0.7f);
            like.setUpdateTime(new Date());
            recommendMapper.updateRecord(like);
        }
    }
    public void UserLikeItem(int uid,int item,float value){
        List<UserItemLike> temp= recommendMapper.selectRecord(uid,item);
        if(temp.size()>1){
            recommendMapper.deleteRecord(uid,item);
            temp=null;
        }
        UserItemLike like=new UserItemLike();
        like.setItemId(item);
        like.setUserId(uid);
        like.setUpdateTime(new Date());
        if(temp==null||temp.size()==0){
            like.setPreference(value);
            recommendMapper.insertSelective(like);
        }
        else{
            like.setPreference(temp.get(0).getPreference()+value);
            recommendMapper.updateRecord(like);
        }
    }
    public void UserUlikeItem(int uid,int item,float value){
        List<UserItemLike> temp= recommendMapper.selectRecord(uid,item);
        if(temp.size()>1){
            recommendMapper.deleteRecord(uid,item);
            temp=null;
        }
        if(temp!=null&&temp.size()!=0){
            UserItemLike like=new UserItemLike();
            like.setItemId(item);
            like.setUserId(uid);
            like.setPreference(temp.get(0).getPreference()-value);
            like.setUpdateTime(new Date());
            recommendMapper.updateRecord(like);
        }
    }
    public void UserFollowSomeone(int uid,int follow){
        int count=0;
        List<Integer> list= followMapper.getUserSubmitCourses(follow,count++,10);
        while(list.size()==10){
            for(int i=0;i<list.size();i++) {
                UserLikeItem(uid,list.get(i));
            }
            list=followMapper.getUserSubmitCourses(follow,count++,10);
        }
        for(int i=0;i<list.size();i++) {
            UserLikeItem(uid,list.get(i));
        }
    }
    public void UserUfollowSomeone(int uid,int follow){
        int count=0;
        List<Integer> list= followMapper.getUserSubmitCourses(follow,count++,10);
        while(list.size()==10){
            for(int i=0;i<list.size();i++) {
                UserUlikeItem(uid,list.get(i));
            }
            list=followMapper.getUserSubmitCourses(follow,count++,10);
        }
        for(int i=0;i<list.size();i++) {
            UserUlikeItem(uid,list.get(i));
        }
    }
    public void UserlikeAlbum(int uid,int album,float value){
        int count=0;
        List<Integer> list= recommendMapper.getCoursesFromAlbum(album,count++,10);
        while(list.size()==10){
            for(int i=0;i<list.size();i++) {
                UserLikeItem(uid,list.get(i),value);
            }
            list=recommendMapper.getCoursesFromAlbum(album,count++,10);
        }
        for(int i=0;i<list.size();i++) {
            UserLikeItem(uid,list.get(i),value);
        }
    }
    public void UserUlikeAlbum(int uid,int album){
        int count=0;
        List<Integer> list= recommendMapper.getCoursesFromAlbum(album,count++,10);
        while(list.size()==10){
            for(int i=0;i<list.size();i++) {
                UserUlikeItem(uid,list.get(i));
            }
            list=recommendMapper.getCoursesFromAlbum(album,count++,10);
        }
        for(int i=0;i<list.size();i++) {
            UserUlikeItem(uid,list.get(i));
        }
    }
    public List<UserItemLike> getUpdates(Date lastUpdate){
        Format format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateTime=format.format(lastUpdate);
        return recommendMapper.getUpdates(updateTime);
    }
    public List<CourseSimpleVO> getCourseList(List<Long>courseList){
        List<Course>temp=recommendMapper.getCourseList(courseList);
        List<CourseSimpleVO>result=new ArrayList<CourseSimpleVO>();
        for(Course c:temp){
            if(c !=null) {
                Integer id = c.getCourseId();
                CourseSimpleVO simpleVO = new CourseSimpleVO(id, c.getCourseTitle(), c.getCourseCover(), c.getCourseIntro(), userMapper.selectByPrimaryKey(c.getUserId()).getNickName(), c.getLevelId(), courseService.getLabelList(id), c.getDiyLabel());
                result.add(simpleVO);
            }
        }
        return result;
    }
}
