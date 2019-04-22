package com.example.handy.core.bean;

public class RecommendAlbumData {
    private int albumId;
    private String albumName;
    private String albumDetail;
    private int authorId;
    private String albumPic;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAlbumPic() {
        return albumPic;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }
}
