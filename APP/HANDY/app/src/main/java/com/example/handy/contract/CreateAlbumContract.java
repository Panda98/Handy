package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.vo.CreateAlbumView;

import java.io.File;
import java.util.List;

public interface CreateAlbumContract {

    interface View extends AbstractView {

        /**
         * 图片上传结果
         *
         * @param url String
         *
         */
        void afterUploadPic(String url);

        /**
         * 创建教程
         *
         */
        void afterCreate(String message);

    }

    interface Presenter extends AbstractPresenter<CreateAlbumContract.View> {

        /**
         * 创建教程
         *
         * @param createAlbumView CreateAlbumView
         */
        void createAlbum(CreateAlbumView createAlbumView);

        /**
         *  上传封面
         *
         * @param file File
         *
         */
        void uploadPic(File file);
    }
}
