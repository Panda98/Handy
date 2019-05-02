package com.example.handy.presenter;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.FollowData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class FollowPagerPresenter extends BasePresenter<FollowPagerContract.View> implements FollowPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public FollowPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(FollowPagerContract.View view) {
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
        getFollowDataList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getFollowDataList(getLoginAccount(),mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<FollowData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<FollowData> followDataList) {
                        mView.showFollowData(followDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getFollowDataList(boolean isShowError) {

        addSubscribe(mDataManager.getFollowDataList(getLoginAccount(),mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<FollowData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        isShowError) {
                    @Override
                    public void onNext(List<FollowData> followDataList) {
                        mView.showFollowData(followDataList, isRefresh);
                    }
                }));
    }
}
