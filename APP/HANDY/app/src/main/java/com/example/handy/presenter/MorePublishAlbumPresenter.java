package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.MorePublishAlbumContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class MorePublishAlbumPresenter extends BasePresenter<MorePublishAlbumContract.View> implements MorePublishAlbumContract.Presenter {

    private DataManager mDataManager;
    private boolean isRefresh = true;

    @Inject
    public MorePublishAlbumPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void autoRefresh(int userId, boolean isShowError) {
        isRefresh = true;
        if (userId == 0)
            userId = getLoginAccount();
        getMyPublishAlbumDataList(userId, isShowError);
    }


    @Override
    public void getMyPublishAlbumDataList(int userId, boolean isShowError) {
        if (userId == 0)
            userId = getLoginAccount();
        addSubscribe(mDataManager.getUserSharedAlbumList(userId)
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
