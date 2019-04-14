package com.handy.support.service.Album;

import com.handy.support.entity.Album;
import com.handy.support.pojo.album.dto.AlbumCourseDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumVO;

import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */

public interface IAlbumService {
    List<AlbumVO> getRecommendedAlbum(int uid);
    List<AlbumDto> getAlbumList(int uid);
    List<AlbumCourseDto> getAlbumDetail(int albumid);
}
