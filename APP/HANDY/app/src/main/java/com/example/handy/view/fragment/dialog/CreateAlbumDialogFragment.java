package com.example.handy.view.fragment.dialog;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.handy.R;
import com.example.handy.app.Constants;
import com.example.handy.app.HandyAPP;
import com.example.handy.base.fragment.BaseDialogFragment;
import com.example.handy.contract.CreateAlbumContract;
import com.example.handy.contract.SelectAlbumContract;
import com.example.handy.core.vo.CreateAlbumView;
import com.example.handy.presenter.CreateAlbumPresenter;
import com.example.handy.presenter.SelectAlbumPresenter;
import com.example.handy.utils.CommonUtils;
import com.example.handy.wigdet.CircularRevealAnim;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.just.agentweb.ActionActivity.REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAlbumDialogFragment extends BaseDialogFragment<CreateAlbumPresenter> implements
        CreateAlbumContract.View,
        CircularRevealAnim.AnimListener,
        ViewTreeObserver.OnPreDrawListener {

    @BindView(R.id.complete_create_album_btn)
    TextView mCompleteBtn;
    @BindView(R.id.album_cover_upload)
    ImageView mAlbumCover;
    @BindView(R.id.album_title_edit_text)
    EditText mAlbumTitle;
    @BindView(R.id.album_desc_edit_text)
    EditText mAlbumDesc;
    @BindView(R.id.album_cover_upload_text)
    TextView mUploadText;

    private List<String> pathList;
    private CreateAlbumView createAlbumView;
    private CircularRevealAnim mCircularRevealAnim;


    @OnClick({R.id.album_cover_upload, R.id.complete_create_album_btn})
    void onClick(View v) {
        switch (v.getId()) {

            case R.id.album_cover_upload:
                uploadPic();
                break;

            case R.id.complete_create_album_btn:
                if (checkInput()) {
                    File file = new File(pathList.get(0));
                    mPresenter.uploadPic(file);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
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
        int height = (int) (metrics.heightPixels * 0.8);
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
        mCircularRevealAnim.hide(mCompleteBtn, mRootView);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_create_album_dialog;
    }

    @Override
    protected void initEventAndData() {
        initCircleAnimation();
        pathList = new ArrayList<>();
    }

    protected void uploadPic(){
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        int cropAspectX,cropAspectY,outputX,outputY;
        cropAspectX = 250;
        cropAspectY = 250;
        outputX = 250;
        outputY = 250;

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
                .cropSize(cropAspectX, cropAspectY, outputX, outputY)
                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(1)
                .build();

        // 跳转到图片选择器
        ISNav.getInstance().toListActivity(this, config, Constants.ALBUM_COVER_UPLOAD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 图片选择结果回调
        if (requestCode == Constants.ALBUM_COVER_UPLOAD && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra("result");
            mAlbumCover.setImageBitmap(loadImg(pathList.get(0)));
            System.out.println(pathList);
            mUploadText.setVisibility(View.INVISIBLE);
        }
    }

    private Bitmap loadImg(String url){
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean checkInput(){

        if(pathList.size() == 0) {
            CommonUtils.showMessage(getActivity(), "请上传一张封面图");
            return false;
        }
        if (TextUtils.isEmpty(mAlbumTitle.getText().toString().trim())) {
            CommonUtils.showMessage(getActivity(), "专辑名不能为空");
            return false;
        }

        return true;
    }

    @Override
    public boolean onPreDraw() {
        mCompleteBtn.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mCompleteBtn, mRootView);
        return true;
    }

    @Override
    public void afterUploadPic(String url) {
        System.out.println(url);
        createAlbumView = new CreateAlbumView(mAlbumTitle.getText().toString().trim(), mAlbumDesc.getText().toString().trim(),
                                                true, mPresenter.getLoginAccount(), url);

        mPresenter.createAlbum(createAlbumView);

    }

    @Override
    public void afterCreate(String message) {
        CommonUtils.showMessage(getActivity(), message);
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
