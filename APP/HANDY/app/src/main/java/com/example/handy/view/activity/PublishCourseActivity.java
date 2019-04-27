package com.example.handy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.core.bean.MaterialItemData;
import com.example.handy.presenter.PublishCoursePresenter;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.CourseEditorMaterialAdapter;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.Constant;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class PublishCourseActivity  extends BaseActivity<PublishCoursePresenter> implements PublishCourseContract.View {


    @BindView(R.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.common_toolbar_title_tv)
    TextView mTitleTv;
    @BindView(R.id.publish_course_rv)
    RecyclerView materialRecycleView;


    LinearLayout mHeaderGroup;
    LinearLayout mFooterGroup;
    LinearLayout headerLayout;
    LinearLayout footerLayout;

    Button picUploadBtn;
    Button addRowBtn;

    private CourseEditorMaterialAdapter courseEditorMaterialAdapter;

    private List<MaterialItemData> materialItemData;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish_course;
    }

    @Override
    protected void initToolbar() {

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        mTitleTv.setText(getString(R.string.publish_course_toolbar_title));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initRecyclerView();

    }

    @Override
    protected void initEventAndData() {

    }

    private void initHeader() {
        headerLayout = mHeaderGroup.findViewById(R.id.publish_course_header);
        picUploadBtn = mHeaderGroup.findViewById(R.id.picture_upload);

        picUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPic(view);
            }
        });
    }
    private void initFooter(){
        footerLayout = mFooterGroup.findViewById(R.id.publish_course_footer);
        addRowBtn = mFooterGroup.findViewById(R.id.add_row);

        addRowBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addRow(view);
            }
        });
    }

    private void initRecyclerView() {
        materialItemData = new ArrayList<>();
        MaterialItemData data = new MaterialItemData();
        courseEditorMaterialAdapter = new CourseEditorMaterialAdapter(R.layout.item_material_view, materialItemData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);

        mHeaderGroup = ((LinearLayout) LayoutInflater.from(this).inflate(R.layout.publish_course_header, null));
        mFooterGroup = ((LinearLayout) LayoutInflater.from(this).inflate(R.layout.publish_course_footer, null));

        initHeader();
        initFooter();

        mHeaderGroup.removeView(headerLayout);
        mFooterGroup.removeView(footerLayout);

        courseEditorMaterialAdapter.addHeaderView(headerLayout);
        courseEditorMaterialAdapter.addFooterView(footerLayout);

        materialRecycleView.setLayoutManager(layoutManager);
        materialRecycleView.setHasFixedSize(true);

        materialRecycleView.setAdapter(courseEditorMaterialAdapter);

    }




//    @OnClick({R.id.picture_upload,R.id.add_row})
//    protected void onClick(View view){
//        switch (view.getId()){
//            case R.id.picture_upload:
//                uploadPic(view);
//                break;
//            case R.id.add_row:
//                addRow(view);
//                break;
//        }
//    }

    protected void addRow(View view){
        materialItemData.add(new MaterialItemData());
        courseEditorMaterialAdapter.replaceData(materialItemData);
    }

    protected void uploadPic(View view){
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(false)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#3F51B5"))
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(Color.parseColor("#3F51B5"))
                // 裁剪大小。needCrop为true的时候配置
                .cropSize(1, 1, 200, 200)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();

// 跳转到图片选择器
        ISNav.getInstance().toListActivity(this, config, Constants.PIC_REQUET_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == Constants.PIC_REQUET_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            String imgString = pic2String(pathList.get(0));
            System.out.println(pathList);
        }
    }

    private String pic2String(String imgPath){
        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,
                options);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // 压缩图片
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byte_arr = stream.toByteArray();
        // Base64图片转码为String
        String encodedString = Base64.encodeToString(byte_arr, 0);
        return encodedString;
    }
}
