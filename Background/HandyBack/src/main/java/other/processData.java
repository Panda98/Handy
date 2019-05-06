package other;


import com.handy.support.pojo.Follow.vo.FollowVO;
import com.handy.support.pojo.recommend.dto.UserItemLike;
import com.handy.support.recommend.operation.Recommend;
import com.handy.support.service.Recommend.RecommendServiceImpl;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
public class processData {
    @After("execution(* com.handy.web.controller.CourseController.likeCourse(int,int)) "+
            "&& args(userId,courseId)")
    public void likeCourse(int userId,int courseId){
        System.out.print("qwe");
        //Recommend.recommendService.UserLikeItem(userId,courseId);
    }
    @After("execution(* com.handy.web.controller.CourseController.unlike(int,int))"+
            "&& args(userId,courseId)")
    public void unLikeCourse(int userId,int courseId){
        System.out.print("qwe");
        //Recommend.recommendService.UserUlikeItem(userId,courseId);
    }
    @After("execution(* com.handy.web.controller.CourseController.collect(int,int))" +
           "&& args(courseId,albumId)")
   public void collectCourse(int courseId,int albumId){
        System.out.print("qwe");
       // recommendService.UserlikeAlbum(uid,albumid);
    }
    @After("execution(* com.handy.web.controller.CourseController.uncollect(int,int))"+
            "&& args(courseId,albumId)")
    public void unCollectCourse(int courseId,int albumId){
        System.out.print("qwe");
        //recommendService.UserUlikeAlbum(uid,albumid);
    }
    @After("execution(* com.handy.web.controller.AlbumController.collectAlbum(int,int))"+
            "&& args(uid,albumid)")
    public void collectAlbum(int uid,int albumid){
        System.out.print("qwe");
        //Recommend.recommendService.UserlikeAlbum(uid,albumid);
    }
    @After("execution(* com.handy.web.controller.AlbumController.uncollectAlbum(int,int))"+
            "&& args(uid,albumid)")
    public void unCollectAlbum(int uid,int albumid){
        System.out.print("qwe");
        //Recommend.recommendService.UserUlikeAlbum(uid,albumid);
    }
    @After("execution(* com.handy.web.controller.FollowController.followSomeone(..))"+
            "&& args(follow)")
    public void followSomeone(FollowVO follow){
        System.out.print("qwe");
        //Recommend.recommendService.UserFollowSomeone(follow.getUid(),follow.getFollow_id());
    }
    @After("execution(* com.handy.web.controller.FollowController.unFollowSomeone(..))"+
            "&& args(follow)")
    public void unFollowSomeone(FollowVO follow){
        System.out.print("qwe");
       // Recommend.recommendService.UserUfollowSomeone(follow.getUid(),follow.getFollow_id());
    }

}
