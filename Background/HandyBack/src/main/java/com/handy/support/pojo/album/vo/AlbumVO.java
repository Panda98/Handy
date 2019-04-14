package com.handy.support.pojo.album.vo;

import com.handy.support.pojo.album.dto.AlbumDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumVO {
    private Integer albumId;
    private String albumName;
    private String albumDetail;
    private Integer userId;
    private String albumPic;

    public AlbumVO(){
        super();
    }
    public AlbumVO(AlbumDto dto){
        BeanUtils.copyProperties(dto,this);
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDetail() {
        return albumDetail;
    }

    public void setAlbumDetail(String albumDetail) {
        this.albumDetail = albumDetail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }
}
