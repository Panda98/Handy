package com.handy.support.service.Album;

import com.handy.support.entity.Album;
import com.handy.support.entity.AlbumCourse;
import com.handy.support.pojo.album.dto.AlbumCourseDto;
import com.handy.support.pojo.album.dto.AlbumCourseInfoDto;
import com.handy.support.pojo.album.dto.AlbumDto;
import com.handy.support.pojo.album.vo.AlbumVO;
import com.handy.support.utils.status.ErrorEnum;

import java.util.List;

/**
 * Created by Pan on 2019/4/14.
 */

public interface IAlbumService {
    ErrorEnum createAlbum(AlbumDto dto);
    List<AlbumDto> getRecommendedAlbum(int uid);
    List<AlbumDto> getAlbumListByUserID(int uid);
    List<AlbumDto> getCollectedAlbum(int uid);
    List<AlbumCourseInfoDto> getAlbumDetail(int albumid, int start, int n);
    AlbumDto getAlbumBriefInfo(int albumid);
    ErrorEnum collect(int uid, int albumid);
    ErrorEnum uncollect(int uid, int albumid);
}
