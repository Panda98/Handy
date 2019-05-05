package com.example.handy.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.handy.R;
import com.example.handy.base.fragment.BaseDialogFragment;
import com.example.handy.contract.CommentDialogContract;
import com.example.handy.contract.SelectAlbumContract;
import com.example.handy.presenter.CommentDialogPresenter;
import com.example.handy.presenter.SelectAlbumPresenter;
import com.example.handy.wigdet.CircularRevealAnim;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialogFragment extends BaseDialogFragment<CommentDialogPresenter> implements
        CommentDialogContract.View,
        CircularRevealAnim.AnimListener,
        ViewTreeObserver.OnPreDrawListener {


    @Override
    protected int getLayout() {
        return R.layout.fragment_comment_dialog;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean onPreDraw() {
        return false;
    }

    @Override
    public void showCommentStatus(Boolean status) {

    }

    @Override
    public void onHideAnimationEnd() {

    }

    @Override
    public void onShowAnimationEnd() {

    }
}
