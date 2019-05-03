package com.example.handy.view.fragment;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.fragment.BaseRootFragment;
import com.example.handy.contract.MainPagerContract;
import com.example.handy.core.bean.BannerData;
import com.example.handy.core.bean.RecommendAlbumData;
import com.example.handy.core.bean.RecommendCourseData;
import com.example.handy.presenter.MainPagerPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.utils.JudgeUtils;
import com.example.handy.view.activity.PublishCourseActivity;
import com.example.handy.view.activity.RegisterActivity;
import com.example.handy.view.adapter.RecommendAlbumAdapter;
import com.example.handy.view.adapter.RecommendCourseAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPagerFragment extends BaseRootFragment<MainPagerPresenter>
        implements MainPagerContract.View {

    @BindView(R.id.normal_view)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.main_pager_rv)
    RecyclerView mRecyclerView;

    //add head banner
    LinearLayout mHeaderGroup;

    private GridView gridView;
    private GridView albumGridView;
    //定义以及初始化Menu数据
    private List<Map<String,Object>> dataList;
    private int[] icon =
            {
                    R.drawable.ic_class1, R.drawable.ic_class2, R.drawable.ic_class3, R.drawable.ic_class4,
                    R.drawable.ic_class5, R.drawable.ic_class6, R.drawable.ic_class7, R.drawable.ic_class8
            };
    private String[] iconName =
            {
                    "纸艺","布艺","花艺","手绘",
                    "书法","织物","饰品","雕刻"
            };

    LinearLayout linearLayout;
    SliderLayout sliderLayout;
    PagerIndicator indicator;

    private List<RecommendCourseData> recommendCourseData;
    private RecommendCourseAdapter mAdapter;
    private SimpleAdapter menuAdapter;
    private RecommendAlbumAdapter albumAdapter;
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

    // 顶部发布教程按钮
    @OnClick({R.id.main_pager_publish})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_pager_publish:
                startActivity(new Intent(getActivity(), PublishCourseActivity.class));
                break;
            default:
                break;
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
        mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.main_pager_header, null));
        initHeader();
        mHeaderGroup.removeView(linearLayout);
        mAdapter.addHeaderView(linearLayout);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initHeader() {
        linearLayout = mHeaderGroup.findViewById(R.id.main_pager_header);
        sliderLayout = mHeaderGroup.findViewById(R.id.main_pager_slider);
        indicator = mHeaderGroup.findViewById(R.id.main_pager_indicator);
        gridView = mHeaderGroup.findViewById(R.id.main_pager_gridView);
        albumGridView = mHeaderGroup.findViewById(R.id.main_pager_recommend_album);

        initMenu();
    }

    private void initMenu() {

        dataList = new ArrayList<>();
        menuAdapter = new SimpleAdapter(getContext(), getData(), R.layout.item_main_pager_menu,new String[]{"image","text"},
                new int[]{R.id.label_image,R.id.label_text});
        gridView.setAdapter(menuAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"我是"+iconName[i],Toast.LENGTH_SHORT).show();
                Log.i("tag","我是"+iconName[i]);
            }
        });
    }

    private List<Map<String,Object>> getData(){
        for(int i=0;i<icon.length;i++){
            Map<String,Object>map=new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }


    @Override
    public void showAutoLoginSuccess() {

    }

    @Override
    public void showAutoLoginFail() {

    }

    @Override
    public void showBannerData(List<BannerData> bannerDataList) {
        sliderLayout.removeAllSliders();
        for (BannerData bannerData : bannerDataList) {
            DefaultSliderView sv = new DefaultSliderView(getActivity());
            sv.image(bannerData.getCourseCover());
            sliderLayout.addSlider(sv);
        }
    }

    @Override
    public void showRecommendAlbumList(List<RecommendAlbumData> recommendAlbumData) {

        albumAdapter = new RecommendAlbumAdapter(getActivity(), R.layout.item_recommend_album, recommendAlbumData);
        albumGridView.setAdapter(albumAdapter);
        albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"我是"+iconName[i],Toast.LENGTH_SHORT).show();
                Log.i("tag","我是"+iconName[i]);
            }
        });
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

        // 点击跳转事件
        mAdapter.setOnItemClickListener((adapter, view, position) -> startCourseDetailPager(view, position));


        showNormal();
    }

    private void startCourseDetailPager(View view, int position) {
        if (mAdapter.getData().size() <= 0 || mAdapter.getData().size() < position) {
            return;
        }

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, getString(R.string.share_view));
        JudgeUtils.startCourseDetailActivity(_mActivity,
                options,
                mAdapter.getData().get(position).getCourseId(),
                mAdapter.getData().get(position).getCourseTitle()
        );
    }

}
