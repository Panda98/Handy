package com.example.handy.core.bean;

import android.support.v4.app.Fragment;

/**
 *  专辑简要信息
 */
public class AlbumCoverData {

    private int albumId;
    private String albumName;
    private String albumDetail;
    private int authorId;
    private String authorName;
    private String albumPic;

    public String getAuthorName() {
        return authorName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumDetail() {
        return albumDetail;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAlbumPic() {
        return albumPic;
    }
}
