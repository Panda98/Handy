package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CreateAlbumContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.core.vo.CreateAlbumView;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.io.File;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CreateAlbumPresenter extends BasePresenter<CreateAlbumContract.View> implements CreateAlbumContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public CreateAlbumPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void createAlbum(CreateAlbumView data){

        addSubscribe(mDataManager.createAlbum(data)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.publish_fail)) {
                    @Override
                    public void onNext(Object o) {
                        mView.afterCreate("发布成功！");
                    }

                }));

    }


    @Override
    public void uploadPic(File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image",file.getName(),requestFile);

        addSubscribe(mDataManager.uploadImage(part)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<String>(mView,
                        false) {
                    @Override
                    public void onNext(String url) {
                        mView.afterUploadPic(url);

                    }

                }));
    }
}
