package com.icarexm.zhiquwang.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.InviteQrBean;
import com.icarexm.zhiquwang.bean.PosterBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.dialog.ImgSaveDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DistributionPosterActivity extends BaseActivity {

    @BindView(R.id.distribution_poster_img_poster)
    ImageView img_poster;
    private String token;
    private Context mContext;
  //  private String webUrl="file:///android_asset/EmptyView.html";
    private CustomProgressDialog progressDialog;
    private String qr_code="";
    private String poster_url="";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_poster);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
//        //设置可自由缩放网页、JS生效
//        webview.setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));
//        WebSettings webSettings = webview.getSettings();
//        webview.loadUrl(webUrl);
//        //1. 设置于JS交互的权限
//        webSettings.setJavaScriptEnabled(true);
//        //2. 将Java对象映射到JS对象
//        webview.addJavascriptInterface(DistributionPosterActivity.this, "jsObject");
//        webview.loadUrl("javascript:callJS('"+ qr_code +"')");
        InitData();
        img_poster.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new ImgSaveDialog(DistributionPosterActivity.this,poster_url).show();
                return false;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        InitData();
    }

    private void InitData() {
        LoadingDialogShow();
        OkGo.<String>post(RequstUrl.URL.getPoster)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LoadingDialogClose();
                        Log.e("海报",response.body());
                        PosterBean posterBean = new GsonBuilder().create().fromJson(response.body(), PosterBean.class);
                        if (posterBean.getCode()==1){
                            poster_url=RequstUrl.URL.HOST+posterBean.getData().getPoster_url();
                            Glide.with(mContext).load(poster_url).into(img_poster);
                        }
//                        InviteQrBean inviteQrBean = new GsonBuilder().create().fromJson(response.body(), InviteQrBean.class);
//                        if (inviteQrBean.getCode()==1){
//                            InviteQrBean.DataBean data = inviteQrBean.getData();
//                            qr_code = data.getQr_code();
//                            webview.loadUrl("javascript:callJS('"+ qr_code +"')");
//                        }
                    }
                });
    }

    @OnClick({R.id.distribution_poster_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.distribution_poster_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_poster_img_back)) {
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(this);
            }
            progressDialog.show();
        }catch (Exception e){

        }
    }

    //关闭刷新
    public void LoadingDialogClose(){
        try {
            if (progressDialog != null){
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch (Exception e){

        }

    }

}
