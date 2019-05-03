package com.handy.support.pojo.album.vo;

import com.handy.support.pojo.album.dto.AlbumCourseDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumCourseVO {
    private List<AlbumCourseInfoVO> courseList;

    public AlbumCourseVO(){
        courseList = new ArrayList<AlbumCourseInfoVO>();
    }

    public AlbumCourseVO(AlbumCourseDto dto){
        BeanUtils.copyProperties(dto,this);
    }


    public List<AlbumCourseInfoVO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<AlbumCourseInfoVO> courseList) {
        this.courseList = courseList;
    }


}
