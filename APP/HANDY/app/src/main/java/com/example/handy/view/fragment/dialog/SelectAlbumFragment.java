package com.example.handy.view.fragment.dialog;


import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;

import com.example.handy.R;
import com.example.handy.base.fragment.BaseDialogFragment;
import com.example.handy.contract.SelectAlbumContract;
import com.example.handy.core.bean.AlbumCoverData;
import com.example.handy.presenter.SelectAlbumPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.adapter.AlbumCoverDataAdapter;
import com.example.handy.wigdet.CircularRevealAnim;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectAlbumFragment extends BaseDialogFragment<SelectAlbumPresenter> implements
        SelectAlbumContract.View,
        CircularRevealAnim.AnimListener,
        ViewTreeObserver.OnPreDrawListener {

    @BindView(R.id.select_album_title)
    TextView mTitle;
    @BindView(R.id.select_album_rv)
    RecyclerView mRecyclerView;

    private CircularRevealAnim mCircularRevealAnim;

    private List<AlbumCoverData> mAlbumList;
    private AlbumCoverDataAdapter mAdapter;
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
        int height = (int) (metrics.widthPixels * 0.8);
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

    private void initRecyclerView() {
        mAlbumList = new ArrayList<>();
        mAdapter = new AlbumCoverDataAdapter(R.layout.item_album_abstract, mAlbumList);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void backEvent() {
        mCircularRevealAnim.hide(mTitle, mRootView);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_select_album;
    }

    @Override
    protected void initEventAndData() {
        initCircleAnimation();
        initRecyclerView();
        mPresenter.getMyAlbumDataList(true);
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }

        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> collectCourse(position));
    }


    @Override
    public boolean onPreDraw() {
        mTitle.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mTitle, mRootView);
        return true;
    }

    @Override
    public void onHideAnimationEnd() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onShowAnimationEnd() {

    }

    @Override
    public void showMyAlbumList(List<AlbumCoverData> albumCoverDataList, boolean isRefresh) {
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.mAlbumList = albumCoverDataList;
            mAdapter.replaceData(albumCoverDataList);
        } else {
            this.mAlbumList.addAll(albumCoverDataList);
            mAdapter.addData(albumCoverDataList);
        }
        showNormal();
    }

    @Override
    public void showCollectResult() {
        CommonUtils.showMessage(getActivity(), "收藏成功");
        backEvent();
    }

    private void collectCourse(int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }
        int albumId = mAdapter.getData().get(position).getAlbumId();
        mPresenter.collectCourse(courseId, albumId);

    }
}
