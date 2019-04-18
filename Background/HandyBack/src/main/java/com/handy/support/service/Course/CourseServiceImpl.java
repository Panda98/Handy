package com.handy.support.service.Course;

import com.handy.support.entity.*;
import com.handy.support.mapper.*;
import com.handy.support.mapper.iMapper.ICourseAlbumMapper;
import com.handy.support.mapper.iMapper.ICourseItemMapper;
import com.handy.support.mapper.iMapper.ICourseLabelMapper;
import com.handy.support.mapper.iMapper.ICourseStepMapper;
import com.handy.support.mapper.iMapper.IHotMapper;
import com.handy.support.mapper.iMapper.IFavorMapper;
import com.handy.support.mapper.iMapper.ICourseMapper;
import com.handy.support.pojo.course.dto.CourseEditDTO;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.pojo.course.vo.CourseDetailVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by joanie on 2019/4/11.
 */
@Service("courseService")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseMapper courseMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ICourseMapper iCourseMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ICourseLabelMapper iCourseLabelMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ICourseItemMapper iCourseItemMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ICourseStepMapper iCourseStepMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private IHotMapper iHotMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AlbumCourseMapper albumCourseMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ICourseAlbumMapper iCourseAlbumMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ViewMapper viewMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private IFavorMapper iFavorMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private FavorMapper favorMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseLabelMapper courseLabelMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseItemMapper courseItemMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseStepMapper courseStepMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ItemMapper itemMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private StepMapper stepMapper;

    public Course getCourseByID(Integer courseId){
        return courseMapper.selectByPrimaryKey(courseId);
    }


    public List<Label> getLabelList(Integer courseId){
        return iCourseLabelMapper.listByCourseId(courseId);
    }

    public List<Item> getItemList(Integer courseId){
        return iCourseItemMapper.listByCourseId(courseId);
    }


    public List<Step> getStepList(Integer courseId){
        return iCourseStepMapper.listByCourseId(courseId);
    }

    public User getAuthor(Integer userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<Hot> getHotList(Integer n){
        return iHotMapper.listMax(n);
    }

    public List<CourseSimpleVO> getBannerList(){
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
        List<Hot> hotList=this.getHotList(3);
        for (Hot h:hotList) {
        Integer id=h.getCourseId();
        Course course=this.getCourseByID(id);
        CourseSimpleVO simpleVO=new CourseSimpleVO(id,course.getCourseTitle(),course.getCourseCover(),course.getCourseIntro(),userMapper.selectByPrimaryKey(course.getUserId()).getNickName(),course.getLevelId(),this.getLabelList(id),course.getDiyLabel());
        simpleList.add(simpleVO);
        }
        return simpleList;
    }

    public List<CourseSimpleVO> getRecommendList(Integer page_no, Integer n){
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
        List<Course> courseList=iCourseMapper.getAll(page_no*n,n);
        for(Course c:courseList){
            Integer id=c.getCourseId();
            CourseSimpleVO simpleVO=new CourseSimpleVO(id,c.getCourseTitle(),c.getCourseCover(),c.getCourseIntro(),userMapper.selectByPrimaryKey(c.getUserId()).getNickName(),c.getLevelId(),this.getLabelList(id),c.getDiyLabel());
            simpleList.add(simpleVO);
        }
        return simpleList;
    }

    public CourseDetailVO getCourseDetail(Integer courseId){
        Course c=this.getCourseByID(courseId);
        Integer authorId=c.getUserId();
        Integer courseViews=c.getCourseViews();
        c.setCourseViews(courseViews+1);
        View view=new View();
        view.setUserId(authorId);
        view.setCourseId(courseId);
        viewMapper.insertSelective(view);
        User author=userMapper.selectByPrimaryKey(authorId);
        return new CourseDetailVO(courseId,c.getCourseTitle(),c.getCourseIntro(),c.getCourseNote(),c.getCourseCover(),c.getCourseViews(),c.getCourseCollects(),c.getCourseLikes(),authorId,author.getNickName(),author.getUserPic(),c.getLevelId(),this.getLabelList(courseId),c.getDiyLabel(),c.getUpdateTime(),this.getItemList(courseId),this.getStepList(courseId));
    }

    public Integer collect(Integer albumId, Integer courseId){
        AlbumCourse ac=new AlbumCourse(albumId,courseId);
        Integer count=albumCourseMapper.insert(ac);
        Course course=this.getCourseByID(courseId);
        Integer courseCollects=course.getCourseCollects();
        course.setCourseCollects(courseCollects+1);
        courseMapper.updateByPrimaryKey(course);
        return count;
    }

    public Integer uncollect(Integer albumId, Integer courseId) {
        Integer count=iCourseAlbumMapper.deleteOne(albumId,courseId);
        Course course=this.getCourseByID(courseId);
        Integer courseCollects=course.getCourseCollects();
        course.setCourseCollects(courseCollects-1);
        courseMapper.updateByPrimaryKey(course);
        return count;
    }

    public Integer isCollected(Integer userId, Integer courseId){
        return iCourseAlbumMapper.isCollected(userId,courseId);
    }

    public Integer likeCourse(Integer userId, Integer courseId){
        Favor f=new Favor(userId,courseId);
        Integer count=favorMapper.insert(f);
        Course course=this.getCourseByID(courseId);
        Integer courseLikes=course.getCourseLikes();
        course.setCourseLikes(courseLikes+1);
        courseMapper.updateByPrimaryKey(course);
        return count;
    }

    public Integer unlikeCourse(Integer userId, Integer courseId){
        Integer count= iFavorMapper.deleteOne(userId,courseId);
        Course course=this.getCourseByID(courseId);
        Integer courseLikes=course.getCourseLikes();
        course.setCourseLikes(courseLikes-1);
        courseMapper.updateByPrimaryKey(course);
        return count;
    }

    public Integer isLiked(Integer userId, Integer courseId){
        return iFavorMapper.isLiked(userId,courseId);
    }

    public List<CourseSimpleVO> getCollectedCourse(Integer albumId){
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
        List<Course> courseList=iCourseAlbumMapper.getCourseList(albumId);
        for(Course c:courseList){
            Integer id=c.getCourseId();
            CourseSimpleVO simpleVO=new CourseSimpleVO(id,c.getCourseTitle(),c.getCourseCover(),c.getCourseIntro(),userMapper.selectByPrimaryKey(c.getUserId()).getNickName(),c.getLevelId(),this.getLabelList(id),c.getDiyLabel());
            simpleList.add(simpleVO);
        }
        return simpleList;
    }

    public List<CourseSimpleVO> getMyCourse(Integer userId,Integer page_no,Integer n){
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
        List<Integer> courseIds=iCourseMapper.getMyCourse(userId,page_no*n,n);
        for(Integer id:courseIds){
            Course c=this.getCourseByID(id);
            CourseSimpleVO simpleVO=new CourseSimpleVO(id,c.getCourseTitle(),c.getCourseCover(),c.getCourseIntro(),userMapper.selectByPrimaryKey(userId).getNickName(),c.getLevelId(),this.getLabelList(id),c.getDiyLabel());
            simpleList.add(simpleVO);
        }
        return simpleList;
    }


    public Integer publishCourse(CourseEditDTO e){
        Course c=new Course();
        c.setCourseTitle(e.getCourseTitle());
        c.setCourseIntro(e.getCourseIntro());
        c.setCourseCover(e.getCourseCover());
        c.setCourseNote(e.getCourseNote());
        c.setDiyLabel(e.getDiyLabel());
        c.setUserId(e.getUserId());
        c.setLevelId(e.getLevelId());
        Integer count=iCourseMapper.insertCourse(c);

        List<Label> labelList=e.getLabelList();
        for(Label l:labelList){
            CourseLabel cl=new CourseLabel(c.getCourseId(),l.getLabelId());
            courseLabelMapper.insert(cl);
        }
        List<Item> itemList=e.getItemList();
        for(Item i:itemList){
            itemMapper.insert(i);
            CourseItem courseItem=new CourseItem(c.getCourseId(),i.getItemId());
            courseItemMapper.insert(courseItem);
        }
        List<Step> stepList=e.getStepList();

        for(Step s:stepList){
            stepMapper.insert(s);
            CourseStep courseStep=new CourseStep(c.getCourseId(),s.getStepId());
            courseStepMapper.insert(courseStep);
        }

        return count;
    }












}
