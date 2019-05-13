package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CollectPagerContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.core.bean.CourseData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class CollectPagerPresenter extends BasePresenter<CollectPagerContract.View> implements CollectPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public CollectPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(CollectPagerContract.View view) {
        super.attachView(view);
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getMyAlbumDataList(isShowError);
        getCollectedAlbumDataList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public void getMyAlbumDataList(boolean isShowError) {
        addSubscribe(mDataManager.getUserSharedAlbumList(getLoginAccount())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<AlbumCoverData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<AlbumCoverData> albumCoverDataList) {
                        mView.showMyAlbumData(albumCoverDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getCollectedAlbumDataList(boolean isShowError){
        addSubscribe(mDataManager.getCollectAlbumList(getLoginAccount())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<AlbumCoverData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<AlbumCoverData> albumCoverDataList) {
                        mView.showMyCollectedAlbumData(albumCoverDataList, isRefresh);
                    }
                }));
    };





}
