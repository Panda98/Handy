package com.example.handy.presenter;

import android.support.annotation.NonNull;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.component.RxBus;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.BaseResponse;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.core.event.LoginEvent;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainPagerPresenter extends BasePresenter<MainPagerContract.View> implements MainPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public MainPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainPagerContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        //addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
        //        .filter(collectEvent -> !collectEvent.isCancelCollectSuccess())
        //        .subscribe(collectEvent -> mView.showCollectSuccess()));
        //
        //addSubscribe(RxBus.getDefault().toFlowable(CollectEvent.class)
        //        .filter(CollectEvent::isCancelCollectSuccess)
        //        .subscribe(collectEvent -> mView.showCancelCollectSuccess()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(LoginEvent::isLogin)
                .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(loginEvent -> mView.showLogoutView()));
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }


    @Override
    public void loadMainPagerData() {
        Observable<BaseResponse<List<BannerData>>> mBannerObservable = mDataManager.getBannerData();
        Observable<BaseResponse<List<RecommendAlbumData>>> mRecommendAlbumObservable = mDataManager.getRecommendAlbumListData(getLoginAccount());
        Observable<BaseResponse<List<RecommendCourseData>>> mRecommendCourseObservable = mDataManager.getRecommendCourseListData(getLoginAccount(),mCurrentPage,Constants.LOAD_NUM);
        addSubscribe(Observable.zip(mBannerObservable, mRecommendAlbumObservable, mRecommendCourseObservable, this::createResponseMap)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<HashMap<String, Object>>(mView) {
                    @Override
                    public void onNext(HashMap<String, Object> map) {
                        // banner
                        BaseResponse<List<BannerData>> bannerResponse = CommonUtils.cast(map.get(Constants.BANNER_DATA));
                        if (bannerResponse != null) {
                            mView.showBannerData(bannerResponse.getData());
                        }
                        // recommend album
                        BaseResponse<List<RecommendAlbumData>> recommendAlbumResponse = CommonUtils.cast(map.get(Constants.RECOMMEND_ALBUM_DATA));
                        if (recommendAlbumResponse != null) {
                            mView.showRecommendAlbumList(recommendAlbumResponse.getData());
                        }
                        // recommend course
                        BaseResponse<List<RecommendCourseData>> recommendCourseResponse = CommonUtils.cast(map.get(Constants.RECOMMEND_COURSE_DATA));
                        if (recommendCourseResponse != null) {
                            mView.showRecommendCourseList(recommendCourseResponse.getData(),isRefresh);
                        }
                    }
                }));
    }

    @Override
    public void getRecommendAlbumList(boolean isShowError) {
        addSubscribe(mDataManager.getRecommendAlbumListData(getLoginAccount())
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<RecommendAlbumData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onNext(List<RecommendAlbumData> recommendAlbumList) {
                        mView.showRecommendAlbumList(recommendAlbumList);
                    }
                }));
    }

    @Override
    public void getRecommendCourseList(boolean isShowError) {
        addSubscribe(mDataManager.getRecommendCourseListData(getLoginAccount(), mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<RecommendCourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_article_list),
                        isShowError) {
                    @Override
                    public void onNext(List<RecommendCourseData> recommendCourseList) {
                        mView.showRecommendCourseList(recommendCourseList,isRefresh);
                    }
                }));
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getRecommendCourseListData(getLoginAccount(), mCurrentPage,Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<RecommendCourseData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_article_list),
                        false) {
                    @Override
                    public void onNext(List<RecommendCourseData> recommendCourseDataList) {
                        mView.showRecommendCourseList(recommendCourseDataList, isRefresh);
                    }
                }));
    }

    @Override
    public void getBannerData(boolean isShowError) {
        addSubscribe(mDataManager.getBannerData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<BannerData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_banner_data),
                        isShowError) {
                    @Override
                    public void onNext(List<BannerData> bannerDataList) {
                        mView.showBannerData(bannerDataList);
                    }
                }));
    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getBannerData(isShowError);
        getRecommendAlbumList(isShowError);
        getRecommendCourseList(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @NonNull
    private HashMap<String, Object> createResponseMap(BaseResponse<List<BannerData>> bannerResponse,
                                                      BaseResponse<List<RecommendAlbumData>> recommendAlbumResponse,
                                                      BaseResponse<List<RecommendCourseData>> recommendCourseResponse) {
        HashMap<String, Object> map = new HashMap<>(3);
        map.put(Constants.BANNER_DATA, bannerResponse);
        map.put(Constants.RECOMMEND_ALBUM_DATA, recommendAlbumResponse);
        map.put(Constants.RECOMMEND_COURSE_DATA, recommendCourseResponse);
        return map;
    }
}