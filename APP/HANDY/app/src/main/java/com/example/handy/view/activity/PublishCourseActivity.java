package com.example.handy.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.base.activity.BaseActivity;
import com.example.handy.contract.PublishCourseContract;
import com.example.handy.core.bean.CourseStepData;
import com.example.handy.core.bean.MaterialItemData;
import com.example.handy.core.bean.MultipleItem;
import com.example.handy.core.bean.PublishCourseData;
import com.example.handy.presenter.PublishCoursePresenter;
import com.example.handy.utils.StatusBarUtil;
import com.example.handy.view.adapter.CourseEditorMultiAdapter;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;


import java.io.ByteArrayOutputStream;
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
    RecyclerView recyclerView;

    private CourseEditorMultiAdapter multiAdapter;

    private PublishCourseData courseData;
    private List<MaterialItemData> materialItemData;
    private List<CourseStepData> stepData;

    private List<MultipleItem> data;



    private int materialNum = 1;
    private int stepNum = 2+materialNum;

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
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.publish_course_button_green), 1f);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());

        mToolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.publish_course_button_green)));


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        initRecyclerView();

    }

    @Override
    protected void initEventAndData() {

    }



    public void initRecyclerView(){
        stepData = new ArrayList<>();
        courseData = new PublishCourseData();

        materialItemData = new ArrayList<>();
        data = new ArrayList<>();
        data.add(new MultipleItem(MultipleItem.HEADER));
        data.add(new MultipleItem(MultipleItem.METERIAL_BTN_VIEW));
        data.add(new MultipleItem(MultipleItem.STEP_VIEW));
        data.add(new MultipleItem(MultipleItem.STEP_BTN_VIEW));
        data.add(new MultipleItem(MultipleItem.TIPS_TEXT));
        multiAdapter = new CourseEditorMultiAdapter(data);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        multiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();

                switch (id){
                    case R.id.picture_upload:
                        uploadPic(view,position);
                        break;
                    case R.id.add_material_row:
                        addMaterialRow(view);
                        break;
                    case R.id.add_step_row:
                        addStepRow(view);
                        break;
                    case R.id.step_picture_upload_btn:
                        uploadPic(view,position);
                        break;
                    case R.id.material_name_edtext:
                        int index = ((MultipleItem)adapter.getItem(position)).getIndex();
                        setMaterialTextEditorListener(view,index);
                        break;
                    case R.id.step_explain_ed:
                        int i = ((MultipleItem)adapter.getItem(position)).getIndex()-1;
                        setStepTextEditorListener(view,i);
                        break;
                    case R.id.publish_course_title_view:
                        setTitleEditorListener(view);
                        break;
                    case R.id.publish_tips_ed:
                        setTipsEditorListener(view);
                        break;


                }
            }
        });


        recyclerView.setAdapter(multiAdapter);
    }

    @OnClick({R.id.publish_course_btn})
    void OnClick(View view){
        if(view.getId() == R.id.publish_course_btn){
            publish();
        }
    }

    protected void addMaterialRow(View view){
        data.add(materialNum,new MultipleItem(MultipleItem.MATERIAL_ITEM,materialNum-1));
        multiAdapter.replaceData(data);
        materialNum++;
        stepNum++;
    }

    protected void addStepRow(View view){

        data.add(stepNum,new MultipleItem(MultipleItem.STEP_ITEM,stepNum-1-materialNum));
        multiAdapter.replaceData(data);
        stepNum++;
    }

    protected void uploadPic(View view,int position){
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

        switch (position){
            case 0:
                ISNav.getInstance().toListActivity(this, config, Constants.COURSE_COVER_UPLAD);
                break;
                default:
                    ISNav.getInstance().toListActivity(this, config, Constants.STEP_PIC_UPLOAD+position-stepNum);
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == Constants.COURSE_COVER_UPLAD && resultCode == RESULT_OK && data != null) {
            //todo: 处理封面图上传
            List<String> pathList = data.getStringArrayListExtra("result");

            System.out.println(pathList);
        }
        if(requestCode >= Constants.STEP_PIC_UPLOAD && resultCode == RESULT_OK && data != null){
            //todo: 处理步骤图上传
            int index = requestCode;
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

    private void publish(){

        courseData.setItemList(materialItemData);
        courseData.setStepList(stepData);

        System.out.println(data);


    }

    private void setMaterialTextEditorListener(View view, int index){
        EditText editText = (EditText)view;
        if(editText.getTag() == null) {
            editText.setTag("material" + index);
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(index>=materialItemData.size()){
                        materialItemData.add(new MaterialItemData());
                    }
                    materialItemData.get(index).setItemName(editable.toString());

                }
            });
        }
    }

    private void setStepTextEditorListener(View view,int index){
        EditText editText = (EditText)view;
        if(editText.getTag() == null) {
            editText.setTag("step" + index);
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(index>=stepData.size()){
                        stepData.add(new CourseStepData());
                    }
                    stepData.get(index).setStepDetail(editable.toString());

                }
            });
        }
    }

    private void setTitleEditorListener(View view){
        EditText editText = (EditText)view;
        if(editText.getTag() == null) {
            editText.setTag("title");
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    courseData.setCourseTitle(editable.toString());
                }
            });
        }
    }

    private void setTipsEditorListener(View view){
        EditText editText = (EditText)view;
        if(editText.getTag() == null) {
            editText.setTag("tip");
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    courseData.setTips(editable.toString());
                }
            });
        }
    }
}
