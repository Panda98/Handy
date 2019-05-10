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
    private int authorId;
    private String authorName;
    private String albumPic;

    public AlbumVO(){
        super();
    }
    public AlbumVO(AlbumDto dto){
        BeanUtils.copyProperties(dto,this);
        this.authorName = dto.getUserName();
        this.authorId = dto.getUserId();
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
