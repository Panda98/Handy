package com.handy.support.service.Course;

import com.handy.support.entity.*;
import com.handy.support.mapper.*;
import com.handy.support.mapper.iMapper.*;
import com.handy.support.pojo.course.dto.CourseEditDTO;
import com.handy.support.pojo.course.dto.ItemDTO;
import com.handy.support.pojo.course.dto.StepDTO;
import com.handy.support.pojo.course.vo.CourseSimpleVO;
import com.handy.support.pojo.course.vo.CourseDetailVO;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.net.SocketException;
import java.util.*;


/**
 * Created by joanie on 2019/4/11.
 */
@Service("courseService")
public class CourseServiceImpl implements ICourseService {

//    @Autowired
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    HttpSolrClient client;

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

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ILabelMapper iLabelMapper;

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

    public List<CourseSimpleVO> getRecommendList(Integer userId,Integer page_no, Integer n){
        List<CourseSimpleVO> simpleList=new ArrayList<CourseSimpleVO>();
        List<Course> courseList=iCourseMapper.getAll(page_no*n,n);

        for(Course c:courseList){
            if(c !=null) {
                Integer id = c.getCourseId();
                CourseSimpleVO simpleVO = new CourseSimpleVO(id, c.getCourseTitle(), c.getCourseCover(), c.getCourseIntro(), userMapper.selectByPrimaryKey(c.getUserId()).getNickName(), c.getLevelId(), this.getLabelList(id), c.getDiyLabel());
                simpleList.add(simpleVO);
            }
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
        return new CourseDetailVO(courseId,c.getCourseTitle(),c.getCourseIntro(),c.getCourseNote(),c.getCourseCover(),c.getCourseViews(),c.getCourseCollects(),c.getCourseLikes(),authorId,author.getNickName(),author.getUserPic(),c.getLevelId(),this.getLabelList(courseId),c.getDiyLabel(),c.getUpdateTime(),this.sortItem(this.getItemList(courseId)),this.sortStep(this.getStepList(courseId)));
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

    public List<Item> sortItem(List<Item> itemList){
        SortList<Item> sortList=new SortList<Item>();
        sortList.Sort(itemList,"getItemTag","asc");
        return itemList;
    }

    public List<Step> sortStep(List<Step> stepList){
        SortList<Step> sortList=new SortList<Step>();
        sortList.Sort(stepList,"getStepTag","asc");
        return stepList;
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
        Integer count=courseMapper.insertSelective(c);
        Integer courseId=iCourseMapper.getLastId();

        List<Label> labelList=e.getLabelList();
        for(Label l:labelList){
            CourseLabel cl=new CourseLabel(courseId,l.getLabelId());
            courseLabelMapper.insert(cl);
        }

        List<ItemDTO> itemList=e.getItemDtoList();
        for(ItemDTO i:itemList){
            Item item=new Item();
            item.setItemName(i.getItemName());
            item.setItemNumber(i.getItemNumber());
            item.setItemTag(i.getItemTag());
            itemMapper.insertSelective(item);
            Integer itemId=iCourseMapper.getLastItemId();
            CourseItem courseItem=new CourseItem(courseId,itemId);
            courseItemMapper.insert(courseItem);
        }

List<StepDTO> stepList=e.getStepDtoList();
        for(StepDTO s:stepList){
            Step step=new Step();
            step.setStepTag(s.getStepTag());
            step.setStepImg(s.getStepImg());
            step.setStepDetail(s.getStepDetail());
            stepMapper.insertSelective(step);
            Integer stepId=iCourseMapper.getLastStepId();
            CourseStep courseStep=new CourseStep(courseId,stepId);
            courseStepMapper.insert(courseStep);
        }

        return count;
    }

//public List<CourseSimpleVO> getSearchedCourse(String text) throws IOException, SolrServerException{
//
//
//
//
//
//
//
//        return null;
//    }


    public FTPClient getFTPClient(String ftpHost, String ftpUserName,
                                  String ftpPassword, int ftpPort) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                System.out.println("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }
    public String uploadImg(String ftpHost, String ftpUserName,
                            String ftpPassword, int ftpPort, String ftpPath,
                            String fileName, MultipartFile file){
        String imgUrl=null;

        FTPClient ftpClient = null;
        try {
            int reply;
            ftpClient = this.getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return imgUrl;
            }
            ftpClient.setControlEncoding("UTF-8"); // 中文支持
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            InputStream input = file.getInputStream();
            if(ftpClient.storeFile(fileName, input)){
                imgUrl="http://106.13.106.249:8080/static/img/upload/"+fileName;
            }


            input.close();
            ftpClient.logout();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }

        return imgUrl;
    }

    public byte[] image2byte(String path) {
        // 定义byte数组
        byte[] data = null;
        // 输入流
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }
public List<Label> getLabels(){
   return iLabelMapper.getAll();
}


}









