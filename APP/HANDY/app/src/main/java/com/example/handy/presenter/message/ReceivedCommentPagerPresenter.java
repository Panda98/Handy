package com.example.handy.presenter.message;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.FollowPagerContract;
import com.example.handy.contract.message.ReceivedCommentPagerContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.bean.CommentMessageData;
import com.example.handy.core.bean.FollowData;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class ReceivedCommentPagerPresenter extends BasePresenter<ReceivedCommentPagerContract.View> implements ReceivedCommentPagerContract.Presenter {

    private DataManager mDataManager;
    private int mCurrentPage;
    private boolean isRefresh = true;

    @Inject
    public ReceivedCommentPagerPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;


    }

    @Override
    public void autoRefresh(boolean isShowError) {
        isRefresh = true;
        mCurrentPage = 0;
        getReceiveMessage(isShowError);
    }

    @Override
    public void loadMore() {
        isRefresh = false;
        mCurrentPage++;
        loadMoreData();
    }

    @Override
    public void loadMoreData() {
        addSubscribe(mDataManager.getCommentMessage(getLoginAccount(),mCurrentPage, Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CommentMessageData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CommentMessageData> commentMessageData) {
                        mView.showReceiveMessage(commentMessageData, isRefresh);
                    }
                }));
    }

    @Override
    public int getLoginAccount() {
        return mDataManager.getLoginAccount();
    }

    @Override
    public void getReceiveMessage(boolean isShowError){
        addSubscribe(mDataManager.getCommentMessage(getLoginAccount(),mCurrentPage,Constants.LOAD_NUM)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<CommentMessageData>>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(List<CommentMessageData> commentMessageData) {
                        mView.showReceiveMessage(commentMessageData, isRefresh);
                    }
                }));
    };
}
