package com.example.handy.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.presenter.MainPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPagerFragment extends BaseRootFragment<MainPagerPresenter>
        implements MainPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.main_pager_rv)
    RecyclerView mRecyclerView;


    LinearLayout linearLayout;
    SliderLayout sliderLayout;
    PagerIndicator indicator;

    private List<RecommendCourseData> recommendCourseData;
    private RecommendCourseAdapter mAdapter;
    private int articlePosition;
    private boolean isRecreate;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isRecreate = getArguments().getBoolean(Constants.ARG_PARAM1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (sliderLayout != null) {
            sliderLayout.startAutoCycle();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sliderLayout != null) {
            sliderLayout.stopAutoCycle();
        }
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setBanner();
        setRefresh();
        if (loggedAndNotRebuilt()) {
            mPresenter.loadMainPagerData();
        } else {
            mPresenter.autoRefresh(true);
        }
        if (CommonUtils.isNetworkConnected()) {
            showLoading();
        }
    }

    private boolean loggedAndNotRebuilt() {
        return (mPresenter.getLoginAccount()!=0)
                && !isRecreate;
    }

    private void setBanner() {

        //对SliderLayout自定义配置
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setDuration(3000);
        sliderLayout.setCustomIndicator(indicator);
        //设置指示器的位置
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

    }
    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            mPresenter.autoRefresh(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPresenter.loadMore();
            refreshLayout.finishLoadMore(1000);
        });
    }

    public static MainPagerFragment getInstance(boolean param1, String param2) {
        MainPagerFragment fragment = new MainPagerFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recommendCourseData = new ArrayList<>();
        mAdapter = new RecommendCourseAdapter(R.layout.item_recommend_course, recommendCourseData);
        //mAdapter.setOnItemClickListener((adapter, view, position) -> startArticleDetailPager(view, position));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.setHasFixedSize(true);

        //add head banner
        LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.main_pager_header, null));
        linearLayout = mHeaderGroup.findViewById(R.id.main_pager_header);
        sliderLayout = mHeaderGroup.findViewById(R.id.main_pager_slider);
        indicator = mHeaderGroup.findViewById(R.id.main_pager_indicator);
        mHeaderGroup.removeView(linearLayout);
        mAdapter.addHeaderView(linearLayout);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showAutoLoginSuccess() {

    }

    @Override
    public void showAutoLoginFail() {

    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        for (BannerData bannerData : bannerDataList) {
            DefaultSliderView sv = new DefaultSliderView(getActivity());
            sv.image(bannerData.getCourseCover());
            sliderLayout.addSlider(sv);
        }
    }

    @Override
    public void showRecommendAlbumList(List<RecommendAlbumData> recommendAlbumData) {

    }

    @Override
    public void showRecommendCourseList(List<RecommendCourseData> recommendCourseData, boolean isRefresh) {
        if (mPresenter.getCurrentPage() == Constants.TYPE_MAIN_PAGER) {
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
        }
        if (mAdapter == null) {
            return;
        }
        if (isRefresh) {
            this.recommendCourseData = recommendCourseData;
            mAdapter.replaceData(recommendCourseData);
        } else {
            this.recommendCourseData.addAll(recommendCourseData);
            mAdapter.addData(recommendCourseData);
        }
        showNormal();
    }

}
