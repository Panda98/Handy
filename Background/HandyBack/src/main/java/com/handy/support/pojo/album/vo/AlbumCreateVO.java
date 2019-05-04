package com.handy.support.pojo.album.vo;

/**
 * Created by Pan on 2019/5/4.
 */
public class AlbumCreateVO {
    private String albumName;
    private String albumDetail;
    private Boolean albumState;
    private Integer userId;
    private String albumPic;


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

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }
}
