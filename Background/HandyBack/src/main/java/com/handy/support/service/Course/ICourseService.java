package com.handy.support.service.Course;

import com.handy.support.entity.Step;
import com.handy.support.entity.Course;
import com.handy.support.entity.Label;
import com.handy.support.entity.Item;
import com.handy.support.entity.User;
import com.handy.support.entity.Hot;
import com.handy.support.pojo.course.dto.*;
import com.handy.support.pojo.course.vo.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.solr.client.solrj.*;
import org.springframework.web.multipart.MultipartFile;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
/**
 * Created by joanie on 2019/4/11.
 */
public interface ICourseService {
    Course getCourseByID(Integer courseId);

    List<Label> getLabelList(Integer courseId);

    List<Item> getItemList(Integer courseId);

    List<Step> getStepList(Integer courseId);

    User getAuthor(Integer userId);

    List<Hot> getHotList(Integer n);

    List<CourseSimpleVO> getBannerList();

    List<CourseSimpleVO> getRecommendList(Integer userId, Integer page_no, Integer n);

    CourseDetailVO getCourseDetail(Integer courseId);

    Integer collect(Integer CourseId,Integer albumId);

    Integer uncollect(Integer CourseId,Integer albumId);

    Integer likeCourse(Integer userId, Integer courseId);

    Integer unlikeCourse(Integer userId, Integer courseId);

    Integer isCollected(Integer userId, Integer courseId);

    Integer isLiked(Integer userId, Integer courseId);

    List<CourseSimpleVO> getCollectedCourse(Integer albumId);

    List<CourseSimpleVO> getMyCourse(Integer userId,Integer page_no,Integer n);

    Integer publishCourse(CourseEditDTO e);

    List<Item> sortItem(List<Item> itemList);

    List<Step> sortStep(List<Step> stepList);


//    List<CourseSimpleVO> getSearchedCourse(String text) throws IOException, SolrServerException;

    FTPClient getFTPClient(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort);

    String uploadImg(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort, String ftpPath, String fileName, MultipartFile file);



     List<Label> getLabels();

     List<CourseSimpleVO> getCourseByLabel(Integer labelId,Integer page_no,Integer n);

}
