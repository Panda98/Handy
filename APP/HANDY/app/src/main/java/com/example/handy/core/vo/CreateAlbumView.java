package com.example.handy.core.vo;

public class CreateAlbumView {

    private String albumName;
    private String albumDetail;
    private boolean albumState;
    private int userId;
    private String albumPic;

    public CreateAlbumView(String albumName, String albumDetail, boolean albumState, int userId, String albumPic) {
        this.albumName = albumName;
        this.albumDetail = albumDetail;
        this.albumState = albumState;
        this.userId = userId;
        this.albumPic = albumPic;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumDetail(String albumDetail) {
        this.albumDetail = albumDetail;
    }

    public void setAlbumState(boolean albumState) {
        this.albumState = albumState;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAlbumPic(String albumPic) {
        this.albumPic = albumPic;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumDetail() {
        return albumDetail;
    }

    public boolean isAlbumState() {
        return albumState;
    }

    public int getUserId() {
        return userId;
    }

    public String getAlbumPic() {
        return albumPic;
    }
}
