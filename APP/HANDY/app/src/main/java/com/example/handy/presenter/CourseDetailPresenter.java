package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.CommentData;
import com.example.handy.core.bean.CourseDetailData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class CourseDetailPresenter extends BasePresenter<CourseDetailContract.View> implements CourseDetailContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public CourseDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(CourseDetailContract.View view) {
        super.attachView(view);
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void getCourseDetail(boolean isShowError, int courseId) {

        addSubscribe(mDataManager.getCourseDetail(courseId, getLoginAccount())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<CourseDetailData>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_course_detail),
                        isShowError) {
                    @Override
                    public void onNext(CourseDetailData courseDetailData) {
                        mView.showCourseDetail(courseDetailData);
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

    @Override
    public void autoRefresh(int courseId, boolean isShowError) {
        getCommentList(isShowError, courseId);
        getCourseDetail(isShowError, courseId);

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void getCommentList(boolean isShowError, int courseId) {
        addSubscribe(mDataManager.getComment(courseId, mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CommentData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        isShowError) {
                    @Override
                    public void onNext(List<CommentData> commentDataList) {
                        mView.showCommentData(commentDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void likeCourse(int courseId) {
        addSubscribe(mDataManager.like(getLoginAccount(), courseId)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data)) {
                    @Override
                    public void onNext(Object o) {
                    }
                }));
    }

    @Override
    public void unlikeCourse(int courseId) {
        addSubscribe(mDataManager.unLike(getLoginAccount(), courseId)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data)) {
                    @Override
                    public void onNext(Object o) {
                    }
                }));
    }

    @Override
    public void getLikeStatus(int courseId) {
        addSubscribe(mDataManager.isLike(getLoginAccount(), courseId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data)) {

                    @Override
                    public void onNext(Boolean aBoolean) {
                        mView.setLikeStatus(aBoolean);
                    }
                }));
    }
}
