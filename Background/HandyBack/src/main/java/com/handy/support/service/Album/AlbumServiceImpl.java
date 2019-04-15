package com.handy.support.service.Album;

import com.handy.support.entity.Album;
import com.handy.support.entity.AlbumCourse;
import com.handy.support.entity.AlbumCourseExample;
import com.handy.support.entity.AlbumExample;
import com.handy.support.mapper.AlbumCourseMapper;
import com.handy.support.mapper.AlbumMapper;
import com.handy.support.pojo.album.dto.AlbumCourseDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
@Service
public class AlbumServiceImpl implements IAlbumService {
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AlbumMapper albumMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AlbumCourseMapper albumCourseMapper;

    public List<AlbumVO> getRecommendedAlbum(int uid){
        //todo: 完成推荐算法后进行测试
        return AlbumRecommend.getRecommendedAlbums(uid);
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

    public List<AlbumCourseDto> getAlbumDetail(int albumid){
        AlbumCourseExample example = new AlbumCourseExample();
        AlbumCourseExample.Criteria criteria = example.createCriteria();
        criteria.andAlbumIdEqualTo(albumid);
        example.or(criteria);
        List<AlbumCourse> list = albumCourseMapper.selectByExample(example);
        List<AlbumCourseDto> dtos = new ArrayList<AlbumCourseDto>();
        for(AlbumCourse albumCourse:list){
            AlbumCourseDto dto = new AlbumCourseDto();
            BeanUtils.copyProperties(albumCourse,dto);
            dtos.add(dto);
        }
        return dtos;
    }


}
