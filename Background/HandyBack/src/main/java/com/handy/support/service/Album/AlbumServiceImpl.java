package com.handy.support.service.Album;

import com.handy.support.entity.*;
import com.handy.support.mapper.AlbumCourseMapper;
import com.handy.support.mapper.AlbumMapper;
import com.handy.support.mapper.CourseMapper;
import com.handy.support.mapper.UserMapper;
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

    public List<AlbumDto> getRecommendedAlbum(int uid){
        //todo: 完成推荐算法后进行测试
//        return AlbumRecommend.getRecommendedAlbums(uid);
        List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
        List<Album> albums = albumMapper.selectByExample(new AlbumExample());
        int i = 1;
        for(Album album:albums){
            AlbumDto dto = new AlbumDto();
            BeanUtils.copyProperties(album,dto);
            albumDtos.add(dto);
            if(i == 3)
                break;
            i++;
        }
        return albumDtos;
    }

    public List<AlbumDto> getAlbumList(int uid){
        AlbumExample example = new AlbumExample();
        AlbumExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(uid);
        example.or(criteria);
        List<Album> albums = albumMapper.selectByExample(example);
        List<AlbumDto> albumDtos = new ArrayList<AlbumDto>();
        for(Album album: albums){
            AlbumDto dto = new AlbumDto();
            BeanUtils.copyProperties(album,dto);
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
            AlbumCourseInfoDto dto = new AlbumCourseInfoDto();
            BeanUtils.copyProperties(author,dto);
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


        return dto;

    }

    public ErrorEnum collect(int uid,int albumid){
        Album album = albumMapper.selectByPrimaryKey(albumid);
        album.setAlbumState(false);
        album.setUserId(uid);
        albumMapper.insert(album);
        return ErrorEnum.SUCCESS;
    }

    public ErrorEnum uncollect(int uid, int albumid){
        AlbumExample example = new AlbumExample();
        AlbumExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumid).andUserIdEqualTo(uid);
        example.or(criteria);
        albumMapper.deleteByExample(example);
        return ErrorEnum.SUCCESS;

    }

}
