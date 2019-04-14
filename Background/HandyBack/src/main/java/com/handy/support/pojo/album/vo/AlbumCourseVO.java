package com.handy.support.pojo.album.vo;

import com.handy.support.pojo.album.dto.AlbumCourseDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumCourseVO {
    private Integer albumId;
    private Integer courseId;

    public AlbumCourseVO(){
        super();
    }

    public AlbumCourseVO(AlbumCourseDto dto){
        BeanUtils.copyProperties(dto,this);
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
