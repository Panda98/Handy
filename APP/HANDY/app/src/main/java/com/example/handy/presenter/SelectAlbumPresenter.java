package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.SelectAlbumContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class SelectAlbumPresenter extends BasePresenter<SelectAlbumContract.View> implements SelectAlbumContract.Presenter {

    private DataManager mDataManager;
    private boolean isRefresh = true;

    @Inject
    SelectAlbumPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        getMyAlbumDataList(isShowError);
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
                        mView.showMyAlbumList(albumCoverDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void collectCourse(int courseId, int albumId) {
        addSubscribe(mDataManager.collectCourse(courseId,albumId)
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
}
