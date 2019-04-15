package com.handy.support.pojo.album.dto;

import com.handy.support.entity.AlbumCourse;
import com.handy.support.pojo.album.vo.AlbumCourseInfoVO;
import com.handy.support.pojo.album.vo.AlbumCourseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumCourseDto {
    private Integer albumId;
    private List<AlbumCourseInfoDto> courseList;

    public AlbumCourseDto() {
        courseList = new ArrayList<AlbumCourseInfoDto>();
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public List<AlbumCourseInfoDto> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<AlbumCourseInfoDto> courseList) {
        this.courseList = courseList;
    }

    public AlbumCourseVO revert2VO(){
        AlbumCourseVO vo = new AlbumCourseVO();
        vo.setAlbumId(this.getAlbumId());
        for(AlbumCourseInfoDto dto:courseList){
            AlbumCourseInfoVO albumCourseInfoVO = dto.revert2VO();
            vo.getCourseList().add(albumCourseInfoVO);
        }
        return vo;
    }
}
