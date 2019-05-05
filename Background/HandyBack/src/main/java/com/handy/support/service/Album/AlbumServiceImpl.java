package com.handy.support.service.Album;

import com.handy.support.entity.*;
import com.handy.support.mapper.*;
import com.handy.support.mapper.customMapper.MyAlbumCoursesMapper;
import com.handy.support.pojo.album.dto.AlbumCourseDto;
import com.handy.support.pojo.album.dto.AlbumCourseInfoDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumVO;
import com.handy.support.utils.status.ErrorEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
@Service("albumService")
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AlbumMapper albumMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AlbumCourseMapper albumCourseMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private MyAlbumCoursesMapper myAlbumCoursesMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseMapper courseMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserAlbumMapper userAlbumMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CourseLabelMapper courseLabelMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private LabelMapper labelMapper;

    public List<AlbumDto> getRecommendedAlbum(int uid){
        //todo: 完成推荐算法后进行测试
//        return AlbumRecommend.getRecommendedAlbums(uid);
        List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
        List<Album> albums = albumMapper.selectByExample(new AlbumExample());
        int i = 1;
        for(Album album:albums){
            AlbumDto dto = new AlbumDto();
            BeanUtils.copyProperties(album,dto);
            User user = userMapper.selectByPrimaryKey(album.getUserId());
            dto.setUserName(user.getNickName());
            albumDtos.add(dto);
            if(i == 3)
                break;
            i++;
        }
        return albumDtos;
    }

    public List<AlbumDto> getAlbumListByUserID(int uid){
        AlbumExample example = new AlbumExample();
        AlbumExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        example.or(criteria);
        List<Album> albums = albumMapper.selectByExample(example);
        List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
        for(Album album: albums){
            AlbumDto dto = new AlbumDto();
            BeanUtils.copyProperties(album,dto);
            User author = userMapper.selectByPrimaryKey(album.getUserId());
            dto.setUserName(author.getNickName());
            albumDtos.add(dto);
        }
        return albumDtos;
    }

    public List<AlbumCourseInfoDto> getAlbumDetail(int albumid,int start,int n){
        List<AlbumCourse> list = myAlbumCoursesMapper.getCoursesByAlbumLimited(albumid,start,n);
        AlbumCourseDto albumCourseDto = new AlbumCourseDto();
        List<AlbumCourseInfoDto> dtoList = new ArrayList<AlbumCourseInfoDto>();
        for(AlbumCourse albumCourse:list){
            Course course = courseMapper.selectByPrimaryKey(albumCourse.getCourseId());
            User author = userMapper.selectByPrimaryKey(course.getUserId());

            CourseLabelExample example = new CourseLabelExample();
            CourseLabelExample.Criteria criteria = example.createCriteria();
            criteria.andCourseIdEqualTo(course.getCourseId());
            example.or(criteria);
            List<CourseLabel> labels = courseLabelMapper.selectByExample(example);
            List<Label> labelList = new ArrayList<Label>();
            for(CourseLabel label:labels){
                Label l = labelMapper.selectByPrimaryKey(label.getLabelId());
                labelList.add(l);
            }
            AlbumCourseInfoDto dto = new AlbumCourseInfoDto();
            dto.setLabelList(labelList);
            BeanUtils.copyProperties(author,dto);
            dto.setUserNickname(author.getNickName());
            BeanUtils.copyProperties(course,dto);
//            albumCourseDto.getCourseList().add(dto);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public AlbumDto getAlbumBriefInfo(int albumid){
        Album album= albumMapper.selectByPrimaryKey(albumid);
        AlbumDto dto = new AlbumDto();
        BeanUtils.copyProperties(album,dto);
        User user = userMapper.selectByPrimaryKey(album.getUserId());
        dto.setUserName(user.getNickName());
        return dto;

    }

    public ErrorEnum collect(int uid,int albumid){
        UserAlbum userAlbum = new UserAlbum();
        userAlbum.setUserId(uid);
        userAlbum.setAlbumId(albumid);
        int code = userAlbumMapper.insert(userAlbum);
        if(code == 0)
            return ErrorEnum.COLLECT_FAIL;
        return ErrorEnum.SUCCESS;
    }

    public ErrorEnum uncollect(int uid, int albumid){
        UserAlbumExample example = new UserAlbumExample();
        UserAlbumExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumid);
        criteria.andUserIdEqualTo(uid);
        example.or(criteria);
        int code = userAlbumMapper.deleteByExample(example);
        if(code == 0)
            return ErrorEnum.UNCOLLECT_FAIL;
        return ErrorEnum.SUCCESS;

    }

    public List<AlbumDto> getCollectedAlbum(int uid){
        UserAlbumExample example = new UserAlbumExample();
        UserAlbumExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        example.or(criteria);
        List<UserAlbum> userAlbums = userAlbumMapper.selectByExample(example);
        List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
        for(UserAlbum userAlbum:userAlbums){
            Album album = albumMapper.selectByPrimaryKey(userAlbum.getAlbumId());
            AlbumDto dto = new AlbumDto();
            BeanUtils.copyProperties(album,dto);
            User user = userMapper.selectByPrimaryKey(album.getUserId());
            dto.setUserName(user.getNickName());
            albumDtos.add(dto);
        }
        return albumDtos;
    }

    public ErrorEnum createAlbum(AlbumDto dto){
        Album album = new Album();
        BeanUtils.copyProperties(dto,album);
        int code = albumMapper.insert(album);
        //todo: 创建专辑失败
        if(code == 0)
            return ErrorEnum.UPDATE_FAIL;
        return ErrorEnum.SUCCESS;

    }

    public ErrorEnum deleteAlbum(int albumid){
        int code = albumMapper.deleteByPrimaryKey(albumid);
        //todo: 删除失败
        if(code == 0)
            return ErrorEnum.REQUEST_FAIL;
        return ErrorEnum.SUCCESS;
    }

}
