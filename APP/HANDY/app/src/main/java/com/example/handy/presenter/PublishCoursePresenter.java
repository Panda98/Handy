package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublishCoursePresenter extends BasePresenter<PublishCourseContract.View> implements PublishCourseContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public PublishCoursePresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }
    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void publish(PublishCourseData data){
        addSubscribe(mDataManager.uploadCourse(data)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.publish_fail)) {
                    @Override
                    public void onNext(Object o) {
                        mView.afterPublish("发布成功！");
                    }

                }));

    }

    @Override
    public void uploadPic(byte[] imgArr,int index){
        //todo: 上传图片
        //addSubscribe(mDataManager.uploadImage(imgArr)
        //        .compose(RxUtils.rxSchedulerHelper())
        //        .compose(RxUtils.handleResult())
        //        .subscribeWith(new BaseObserver<String>(mView,
        //                false) {
        //            @Override
        //            public void onNext(String url) {
        //                mView.afterUploadPic(url,index);
        //
        //            }
        //
        //        }));
    }
}
