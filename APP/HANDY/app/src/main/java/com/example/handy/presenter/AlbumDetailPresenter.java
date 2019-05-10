package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.AlbumDetailContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class AlbumDetailPresenter extends BasePresenter<AlbumDetailContract.View> implements AlbumDetailContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;
    private int albumId;

    @Inject
    public AlbumDetailPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getAlbumCoverData(boolean isShowError, int albumId) {
        addSubscribe(mDataManager.getAlbumCoverData(albumId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<AlbumCoverData>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_album_list),
                        isShowError) {
                    @Override
                    public void onNext(AlbumCoverData albumCoverData) {
                        mView.showAlbumCoverData(albumCoverData);
                        if (getLoginAccount() == albumCoverData.getAuthorId())
                            mView.showMyAlbumView();
                        else
                            mView.showOthersAlbumView();
                    }
                }));
    }

    @Override
    public void autoRefresh(boolean isShowError, int albumId) {

        this.albumId = albumId;
        isRefresh = true;
        mCurrentPage = 0;
        getAlbumCoverData(isShowError, albumId);
        getAlbumDetailData(isShowError, albumId);
    }

    @Override
    public void loadMore() {

        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getAlbumCourseData(getLoginAccount(), albumId, mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_album_list),
                        false) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showAlbumCourseData(courseDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getAlbumDetailData(boolean isShowError, int albumId) {
        addSubscribe(mDataManager.getAlbumCourseData(getLoginAccount(), albumId, mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_album_list),
                        isShowError) {
                    @Override
                    public void onNext(List<CourseData> courseDataList) {
                        mView.showAlbumCourseData(courseDataList, isRefresh);
                    }
                }));
    }

    // 收藏专辑
    @Override
    public void collectAlbum(int albumId) {
        addSubscribe(mDataManager.collectAlbum(getLoginAccount(),albumId)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(Object o) {
                        mView.showCollectResult();
                    }

                }));
    }

    // 取消收藏专辑
    @Override
    public void unCollectAlbum(int albumId) {
        addSubscribe(mDataManager.unCollectAlbum(getLoginAccount(),albumId)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(Object o) {
                        mView.showUnCollectResult();
                    }

                }));
    }

    // 获得收藏状态
    @Override
    public void isCollectAlbum(int albumId) {
        addSubscribe(mDataManager.isCollectAlbum(getLoginAccount(),albumId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(Boolean status) {
                        mView.setCollectStatus(status);
                    }
                }));
    }

    // 删除收藏的专辑
    @Override
    public void deleteAlbum(int albumId) {
        addSubscribe(mDataManager.deleteAlbum(albumId)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(Object o) {
                    }

                }));
    }
}
