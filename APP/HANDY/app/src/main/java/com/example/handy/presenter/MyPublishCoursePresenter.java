package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MyPublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class MyPublishCoursePresenter extends BasePresenter<MyPublishCourseContract.View> implements MyPublishCourseContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public MyPublishCoursePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getPublishCourseDataList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getUserPublishCourse(getLoginAccount(),mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showMyPublishCourseData(courseDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getPublishCourseDataList(boolean isShowError) {
        addSubscribe(mDataManager.getUserPublishCourse(getLoginAccount(),mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showMyPublishCourseData(courseDataList, isRefresh);
                    }
                }));
    }

}
