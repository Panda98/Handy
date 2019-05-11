package com.example.handy.presenter;

import android.hardware.usb.UsbRequest;
import android.support.annotation.NonNull;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.AuthorHomepageContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.UserInfoData;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AuthorHomepagePresenter extends BasePresenter<AuthorHomepageContract.View> implements AuthorHomepageContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public AuthorHomepagePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(int userId, boolean isShowError) {
        getUserInfo(userId, isShowError);
        getAuthorAlbum(userId, isShowError);
        getAuthorCourse(userId, isShowError);
    }

    @Override
    public void loadAuthorHomepageData(int userId) {
        Observable<BaseResponse<UserInfoData>> mUserInfoObservable = mDataManager.getUserInfo(userId);
        Observable<BaseResponse<List<AlbumCoverData>>> mAlbumCoverObservable = mDataManager.getUserSharedAlbumList(userId);
        Observable<BaseResponse<List<CourseData>>> mAuthorCourseObservable = mDataManager.getUserPublishCourse(userId, 0, 3);
        addSubscribe(Observable.zip(mUserInfoObservable, mAlbumCoverObservable, mAuthorCourseObservable, this::createResponseMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {
                        // user info
                        BaseResponse<UserInfoData> userInfoResponse = CommonUtils.cast(map.get(Constants.USER_INFO_DATA));
                        if (userInfoResponse != null) {
                            mView.showUserInfo(userInfoResponse.getData());
                        }
                        // user album
                        BaseResponse<List<AlbumCoverData>> userAlbumResponse = CommonUtils.cast(map.get(Constants.USER_ALBUM_DATA));
                        if (userAlbumResponse != null) {
                            mView.showUserPublishAlbum(userAlbumResponse.getData());
                        }
                        // user course
                        BaseResponse<List<CourseData>> userCourseResponse = CommonUtils.cast(map.get(Constants.USER_COURSE_DATA));
                        if (userCourseResponse != null) {
                            mView.showUserPublishCourse(userCourseResponse.getData());
                        }
                    }
                }));
    }

    @NonNull
    private HashMap<String, Object> createResponseMap(BaseResponse<UserInfoData> userInfoResponse,
                                                      BaseResponse<List<AlbumCoverData>> userAlbumResponse,
                                                      BaseResponse<List<CourseData>> userCourseResponse) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put(Constants.USER_INFO_DATA, userInfoResponse);
        map.put(Constants.USER_ALBUM_DATA, userAlbumResponse);
        map.put(Constants.USER_COURSE_DATA, userCourseResponse);
        return map;
    }

    @Override
    public void getUserInfo(int userId, boolean isShowError) {
        addSubscribe(mDataManager.getUserInfo(userId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<UserInfoData>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        isShowError) {
                    @Override
                    public void onNext(UserInfoData userInfoData) {
                        mView.showUserInfo(userInfoData);
                    }
                }));
    }

    @Override
    public void getAuthorCourse(int userId, boolean isShowError) {

        addSubscribe(mDataManager.getUserPublishCourse(userId,0,3)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        isShowError) {
                    @Override
                    public void onNext(List<CourseData> courseData) {
                        mView.showUserPublishCourse(courseData);
                    }
                }));
    }

    @Override
    public void getAuthorAlbum(int userId, boolean isShowError) {
        addSubscribe(mDataManager.getUserSharedAlbumList(userId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<AlbumCoverData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        isShowError) {
                    @Override
                    public void onNext(List<AlbumCoverData> albumCoverData) {
                        mView.showUserPublishAlbum(albumCoverData);
                    }
                }));
    }

    @Override
    public void follow(int followId) {
        addSubscribe(mDataManager.follow(getLoginAccount(), followId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_course_detail)) {
                    @Override
                    public void onNext(Boolean status) {
                        System.out.println(status);
                        mView.showFollowView();
                    }

                }));
    }

    @Override
    public void unFollow(int followId) {
        addSubscribe(mDataManager.unFollow(getLoginAccount(), followId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_course_detail)) {
                    @Override
                    public void onNext(Boolean status) {
                        System.out.println(status);
                        mView.showUnFollowView();
                    }

                }));
    }

    @Override
    public void isFollow(int followId) {
        addSubscribe(mDataManager.isFollow(getLoginAccount(), followId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_course_detail)) {
                    @Override
                    public void onNext(Boolean status) {
                        System.out.println(status);
                        mView.setFollowVisibility(getLoginAccount());
                        mView.setFollowStatus(status);

                    }

                }));
    }


}
