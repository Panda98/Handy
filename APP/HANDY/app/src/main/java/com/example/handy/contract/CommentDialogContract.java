package com.example.handy.contract;

import com.example.handy.base.presenter.AbstractPresenter;
import com.example.handy.base.view.AbstractView;

public interface CommentDialogContract {

    interface View extends AbstractView {

        /**
         * Show comment status
         *
         * @param status boolean
         *
         */
        void showCommentStatus(Boolean status);

    }

    interface Presenter extends AbstractPresenter<CommentDialogContract.View> {

        /**
         * comment 评论
         *
         * @param courseId int
         * @param content String
         *
         */
        void comment(int courseId, String content);
    }
}
