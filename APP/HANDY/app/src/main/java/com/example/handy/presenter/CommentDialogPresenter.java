package com.example.handy.presenter;

import android.text.TextUtils;

import com.example.handy.R;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.presenter.BasePresenter;
import com.example.handy.contract.CommentDialogContract;
import com.example.handy.contract.CourseDetailContract;
import com.example.handy.core.DataManager;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.core.vo.PostCommentView;
import com.example.handy.utils.RxUtils;
import com.example.handy.wigdet.BaseObserver;

import java.util.List;

import javax.inject.Inject;

public class CommentDialogPresenter extends BasePresenter<CommentDialogContract.View> implements CommentDialogContract.Presenter {

    private DataManager mDataManager;
    @Inject
    public CommentDialogPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }


    @Override
    public void comment(int courseId, String content) {
        if (TextUtils.isEmpty(content)) {
            mView.showSnackBar("评论内容不能为空");
            return;
        }
        PostCommentView postCommentView = new PostCommentView(getLoginAccount(), courseId, content);
        addSubscribe(mDataManager.postComment(postCommentView)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView,
                        HandyAPP.getInstance().getString(R.string.failed_to_obtain_follow_data),
                        false) {
                    @Override
                    public void onNext(Boolean status) {
                        mView.showCommentStatus(status);
                    }
                }));
    }
}
