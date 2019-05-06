package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CourseListByLabelContract;
import com.example.handy.contract.MorePublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.CourseData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class CourseListByLabelPresenter extends BasePresenter<CourseListByLabelContract.View> implements CourseListByLabelContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public CourseListByLabelPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(int labelId, boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getCourseListByLabel(labelId, isShowError);
    }

    @Override
    public void loadMore(int labelId) {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData(labelId);
    }

    @Override
    public void loadMoreData(int labelId) {
        addSubscribe(mDataManager.getCourseWithLabel(labelId, mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showCourseListByLabel(courseDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getCourseListByLabel(int labelId, boolean isShowError) {

        addSubscribe(mDataManager.getCourseWithLabel(labelId, mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showCourseListByLabel(courseDataList, isRefresh);
                    }
                }));

    }

}
