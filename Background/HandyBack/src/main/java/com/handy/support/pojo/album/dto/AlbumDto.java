package com.handy.support.pojo.album.dto;

/**
 * Created by Pan on 2019/4/14.
 */
public class AlbumDto {
    private Integer albumId;
    private String albumName;
    private String albumDetail;
    private Boolean albumState;
    private Integer userId;
    private String albumPic;

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
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

    public Boolean getAlbumState() {
        return albumState;
    }

    public void setAlbumState(Boolean albumState) {
        this.albumState = albumState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }




}
