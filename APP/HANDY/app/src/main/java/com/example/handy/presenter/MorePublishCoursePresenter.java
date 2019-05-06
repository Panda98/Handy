package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MorePublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.CourseData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class MorePublishCoursePresenter extends BasePresenter<MorePublishCourseContract.View> implements MorePublishCourseContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;
    private int userId;

    @Inject
    public MorePublishCoursePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
        this.userId = getLoginAccount();
    }

    @Override
    public void autoRefresh(int userId, boolean isShowError) {
        if (userId != 0)
            this.userId = userId;
        isRefresh = true;
        mCurrentPage = 0;
        getPublishCourseDataList(this.userId, isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getUserPublishCourse(this.userId,mCurrentPage, Constants.LOAD_NUM)
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
    public void getPublishCourseDataList(int userId, boolean isShowError) {
        if (userId != 0)
            this.userId = userId;

        addSubscribe(mDataManager.getUserPublishCourse(this.userId, mCurrentPage, Constants.LOAD_NUM)
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
