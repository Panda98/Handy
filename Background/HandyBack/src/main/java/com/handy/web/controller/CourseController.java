package com.handy.web.controller;

import com.google.gson.Gson;
import com.handy.support.entity.AlbumCourse;
import com.handy.support.entity.Label;
import com.handy.support.pojo.course.dto.CourseEditDTO;
import com.handy.support.pojo.course.vo.CourseDetailVO;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.service.Course.SolrConnect;
import com.handy.support.utils.status.ErrorEnum;
import com.handy.support.utils.status.ReturnCode;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.handy.support.service.Course.ICourseService;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class CourseController {
//    @Autowired
//    private SolrConnect solrConnect;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private Gson gson;

    /**
     * 获取热门推荐
     * @return json
     */
    @RequestMapping(value = "/main/banner", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    private String getBanner() {
        List<CourseSimpleVO> list = iCourseService.getBannerList();
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/recommend", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getRecommend(int uid,int page_no,int n){
        List<CourseSimpleVO> list=iCourseService.getRecommendList(uid,page_no,n);
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/detail", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getCourseDetail(int courseId){
        CourseDetailVO detailVO=iCourseService.getCourseDetail(courseId);
        ErrorEnum error = null;
        if (detailVO == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<CourseDetailVO> code=new ReturnCode<CourseDetailVO>(error,detailVO);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/publish", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String publish(@RequestBody CourseEditDTO editDTO){
        int result=iCourseService.publishCourse(editDTO);
        ErrorEnum error=null;
        if(result ==1){
            error = ErrorEnum.SUCCESS;
        }else{
            error = ErrorEnum.PUBLISH_FAIL;
        }
        ReturnCode<String> code=new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/collect", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String collect(int albumId, int courseId){
        int result=iCourseService.collect(courseId,albumId);
        ErrorEnum error=null;
        if(result ==1){
            error = ErrorEnum.SUCCESS;
        }else{
            error = ErrorEnum.COLLECT_FAIL;
        }
        ReturnCode<String> code=new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/uncollect", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String uncollect(int albumId, int courseId){
        int result=iCourseService.uncollect(courseId,albumId);
        ErrorEnum error=null;
        if(result ==1){
            error = ErrorEnum.SUCCESS;
        }else{
            error = ErrorEnum.UNCOLLECT_FAIL;
        }
        ReturnCode<String> code=new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/iscollected", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String isCollected(int userId, int courseId){
        Integer result=iCourseService.isCollected(userId,courseId);
        Boolean b=true;
        if(result == null){
            b=false;
        }
        ErrorEnum error=ErrorEnum.SUCCESS;
        ReturnCode<Boolean> code=new ReturnCode<Boolean>(error,b);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/like", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String likeCourse(int userId, int courseId){
        int result=iCourseService.likeCourse(userId,courseId);
        ErrorEnum error=null;
        if(result ==1){
            error = ErrorEnum.SUCCESS;
        }else{
            error = ErrorEnum.LIKE_FAIL;
        }
        ReturnCode<String> code=new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/unlike", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String unlike(int userId, int courseId){
        int result=iCourseService.unlikeCourse(userId,courseId);
        ErrorEnum error=null;
        if(result ==1){
            error = ErrorEnum.SUCCESS;
        }else{
            error = ErrorEnum.UNLIKE_FAIL;
        }
        ReturnCode<String> code=new ReturnCode<String>(error,null);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/isliked", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String isLiked(int userId, int courseId){
        Integer result=iCourseService.isLiked(userId,courseId);
        Boolean b=true;

        if(result == null){
            b=false;
        }
        ErrorEnum error=ErrorEnum.SUCCESS;
        ReturnCode<Boolean> code=new ReturnCode<Boolean>(error,b);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getMyCourse(int uid, int page_no, int n){
        List<CourseSimpleVO> list=iCourseService.getMyCourse(uid,page_no,n);
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/collection", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getCollectedCourse(Integer albumId){
        List<CourseSimpleVO> list=iCourseService.getCollectedCourse(albumId);
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
        return code.returnHandler();
    }

//    @RequestMapping(value = "/course/search", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
//    public String getSearchedCourse(String text){
//        List<CourseSimpleVO> list=null;
//        try {
//            list=solrConnect.courseQuery(text);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        }
//        ErrorEnum error = null;
//        if (list == null) {
//            error = ErrorEnum.REQUEST_FAIL;
//        } else {
//            error = ErrorEnum.SUCCESS;
//        }
//        ReturnCode<List<CourseSimpleVO>> code = new ReturnCode<List<CourseSimpleVO>>(error, list);
//        return code.returnHandler();
//    }

    @RequestMapping(value = "/uploadImg",produces = "application/json; charset=utf-8",method = RequestMethod.POST)
    public String getUploadUrL(@RequestParam("image") MultipartFile file) {
        String imgUrl = null;
        ErrorEnum error = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                DateFormat df = new SimpleDateFormat("yyMMdd_HHmmssSSS");
                String date = df.format(new Date());
                String filename = date+".jpg";
                imgUrl = iCourseService.uploadImg("106.13.106.249", "handy", "handy", 21, "/usr/local/tomcat/apache-tomcat-9.0.17/webapps/HandyBack_war_exploded/static/img/upload", filename, file);
                if(imgUrl == null)
                    error = ErrorEnum.UPLOAD_FAIL;
                else
                    error = ErrorEnum.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ReturnCode<String> code = new ReturnCode<String>(error,imgUrl);
        return code.returnHandler();
    }

    @RequestMapping(value = "/course/label", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public String getLabels(){
        List<Label> list=iCourseService.getLabels();
        ErrorEnum error = null;
        if (list == null) {
            error = ErrorEnum.REQUEST_FAIL;
        } else {
            error = ErrorEnum.SUCCESS;
        }
        ReturnCode<List<Label>> code=new ReturnCode<List<Label>>(error, list);
        return code.returnHandler();
    }




}
