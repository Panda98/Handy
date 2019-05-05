package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MyPublishAlbumContract;
import com.example.handy.contract.MyPublishCourseContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.AlbumListData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class MyPublishAlbumPresenter extends BasePresenter<MyPublishAlbumContract.View> implements MyPublishAlbumContract.Presenter {

    private DataManager mDataManager;
    private boolean isRefresh = true;

    @Inject
    public MyPublishAlbumPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        getMyPublishAlbumDataList(isShowError);
    }


    @Override
    public void getMyPublishAlbumDataList(boolean isShowError) {
        addSubscribe(mDataManager.getUserSharedAlbumList(getLoginAccount())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<AlbumCoverData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<AlbumCoverData> albumListDataList) {
                        mView.showMyPublishAlbumData(albumListDataList, isRefresh);
                    }
                }));
    }

}
