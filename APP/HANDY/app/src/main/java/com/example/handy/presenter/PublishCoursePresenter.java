package com.example.handy.presenter;

import android.provider.MediaStore;

import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.LoginContract;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.PublishCourseData;
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
        Map<String, RequestBody> bodyMap = new HashMap<>();
        bodyMap.put("userId",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),String.valueOf(data.getUserId())));
        bodyMap.put("courseIntro",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),data.getCourseIntro()));
        bodyMap.put("courseTitle",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),data.getCourseTitle()));
        bodyMap.put("courseNote",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),data.getTips()));

        RequestBody coverBody = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"),data.getCourseCover());
        MultipartBody.Part part = MultipartBody.Part.createFormData("cover",data.getCourseCover(),coverBody);
        bodyMap.put("courseCover",coverBody);


        bodyMap.put("levelId",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),String.valueOf(data.getLevelId())));
        Gson gson = new Gson();
        bodyMap.put("labelList",RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),gson.toJson(data.getLabelList())));

        bodyMap.put("diyLabel",RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"),data.getDiyLabel()));

        bodyMap.put("itemList",RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),gson.toJson(data.getItemList())));





    }

    @Override
    public String uploadPic(byte[] imgArr){
        //todo: 上传图片

        return null;
    }
}
