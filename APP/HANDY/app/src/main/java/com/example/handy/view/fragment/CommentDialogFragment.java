package com.example.handy.view.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseDialogFragment;
import com.example.handy.contract.CommentDialogContract;
import com.example.handy.presenter.CommentDialogPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.wigdet.CircularRevealAnim;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentDialogFragment extends BaseDialogFragment<CommentDialogPresenter> implements
        CommentDialogContract.View,
        CircularRevealAnim.AnimListener,
        ViewTreeObserver.OnPreDrawListener {


    @BindView(R.id.comment_edit_text)
    EditText mCommentEdit;

    @BindView(R.id.comment_button)
    Button commentBtn;

    private CircularRevealAnim mCircularRevealAnim;
    private int courseId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
        // 接收关联Activity传来的数据 -----
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.courseId = bundle.getInt("courseId");
            System.out.println(courseId);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int) (metrics.widthPixels * 0.8);
        int height = (int) (metrics.widthPixels * 0.6);
        assert window != null;
        //window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setLayout(width, height);
        window.setGravity(Gravity.CENTER);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);
    }

    private void initCircleAnimation() {
        mCircularRevealAnim = new CircularRevealAnim();
        mCircularRevealAnim.setAnimListener(this);
    }

    public void backEvent() {
        mCircularRevealAnim.hide(mCommentEdit, mRootView);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_comment_dialog;
    }

    @Override
    protected void initEventAndData() {
        initCircleAnimation();
        subscribeCommentClickEvent();
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private void subscribeCommentClickEvent() {
        mPresenter.addRxBindingSubscribe(RxView.clicks(commentBtn)
                .throttleFirst(Constants.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> mPresenter != null)
                .subscribe(o -> mPresenter.comment(
                        courseId,
                        mCommentEdit.getText().toString().trim())));
    }


    @Override
    public boolean onPreDraw() {
        mCommentEdit.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mCommentEdit, mRootView);
        return true;
    }

    @Override
    public void showCommentStatus(Boolean status) {
        if (status)
            CommonUtils.showMessage(getActivity(), "评论成功");
        backEvent();
    }

    @Override
    public void onHideAnimationEnd() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onShowAnimationEnd() {

    }
}
