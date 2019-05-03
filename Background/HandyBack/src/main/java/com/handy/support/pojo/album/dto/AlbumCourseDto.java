package com.handy.support.pojo.album.dto;

import com.handy.support.pojo.album.vo.AlbumCourseInfoVO;
import com.handy.support.pojo.album.vo.AlbumCourseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumCourseDto {
    private List<AlbumCourseInfoDto> courseList;

    public AlbumCourseDto() {
        courseList = new ArrayList<AlbumCourseInfoDto>();
    }


    public List<AlbumCourseInfoDto> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<AlbumCourseInfoDto> courseList) {
        this.courseList = courseList;
    }

    public AlbumCourseVO revert2VO(){
        AlbumCourseVO vo = new AlbumCourseVO();
        for(AlbumCourseInfoDto dto:courseList){
            AlbumCourseInfoVO albumCourseInfoVO = dto.revert2VO();
            vo.getCourseList().add(albumCourseInfoVO);
        }
        return vo;
    }
}
