package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.CertificationBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.contract.CertificationContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.CertificationPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.GifSizeFilter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CertificationActivity extends BaseActivity implements CertificationContract.View {

   @BindView(R.id.certification_rl_TypeOne)
   LinearLayout rl_TypeOne;
   @BindView(R.id.certification_rl_TypeTwo)
   RelativeLayout rl_TypeTwo;
   @BindView(R.id.certification_img_card_frond)
    ImageView img_card_frond;
   @BindView(R.id.certification_img_card_reverse)
   ImageView img_card_reverse;
   @BindView(R.id.certification_edt_username)
   EditText edt_username;
   @BindView(R.id.certification_edt_card_number)
   EditText edt_car_number;
   @BindView(R.id.certification_img_dlt_front)
   ImageView img_dlt_front;
   @BindView(R.id.certification_img_dlt_reverse)
   ImageView img_dlt_reverse;
   @BindView(R.id.certification_rl_audit)
   RelativeLayout rl_audit;
   @BindView(R.id.certification_btn_submit)
    Button btn_submit;

   private int Type=1;
    private CertificationPresenter certificationPresenter;
    private String token;
    private int FRONT_CODE=1600;
    private int REVERSE_CODE=1601;
    private String frondurl="";
    private String reverseurl="";
    private Context mContext;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        //权限申请
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        LoadingDialogShow();
        certificationPresenter = new CertificationPresenter(this);
        certificationPresenter.GetCertification(token);
    }


    @OnClick({R.id.certification_img_back,R.id.certification_img_card_frond,R.id.certification_img_card_reverse,
            R.id.certification_img_dlt_reverse,R.id.certification_img_dlt_front,R.id.certification_btn_submit})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.certification_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.certification_img_back)) {
                    finish();
                }
                break;
            case R.id.certification_btn_submit:
                if (!ButtonUtils.isFastDoubleClick(R.id.certification_btn_submit)) {
                    String car_number = edt_car_number.getText().toString();
                    String username = edt_username.getText().toString();
                    if (!username.equals("")) {
                        if (!car_number.equals("")) {
                            if (!frondurl.equals("")) {
                                if (!reverseurl.equals("")) {
                                    certificationPresenter.GetdoAutoNym(token, username, frondurl, reverseurl, car_number);
                                } else {
                                    ToastUtils.showToast(mContext, "身份证反面不能为空");
                                }
                            } else {
                                ToastUtils.showToast(mContext, "身份证正面不能为空");
                            }
                        } else {
                            ToastUtils.showToast(mContext, "身份证号不能为空");
                        }
                    } else {
                        ToastUtils.showToast(mContext, "姓名不能为空");
                    }
                }
                break;
            case R.id.certification_img_card_frond:
                if (!ButtonUtils.isFastDoubleClick(R.id.certification_img_card_frond)) {
                    try {
                        Matisse.from(this)
                                .choose(MimeType.ofImage(), false)
                                .countable(true)
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true, "com.icarexm.zhiquwang.fileprovider", "test"))
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_110))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .showSingleMediaType(true)
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .autoHideToolbarOnSingleTap(true)
                                .forResult(FRONT_CODE);
                    } catch (Exception e) {
                        //权限申请
                        ToastUtils.showToast(mContext, "请允许权限");
                        XXPermissions.with(this)
                                .request(new OnPermission() {

                                    @Override
                                    public void hasPermission(List<String> granted, boolean isAll) {

                                    }

                                    @Override
                                    public void noPermission(List<String> denied, boolean quick) {

                                    }
                                });
                    }
                }
                break;
            case R.id.certification_img_card_reverse:
                if (!ButtonUtils.isFastDoubleClick(R.id.certification_img_card_reverse)) {
                    try {
                        Matisse.from(this)
                                .choose(MimeType.ofImage(), false)
                                .countable(true)
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true, "com.icarexm.zhiquwang.fileprovider", "test"))
                                .maxSelectable(1)
//                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_110))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .showSingleMediaType(true)
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .autoHideToolbarOnSingleTap(true)
                                .forResult(REVERSE_CODE);
                    } catch (Exception e) {
                        //权限申请
                        ToastUtils.showToast(mContext, "请允许权限");
                        XXPermissions.with(this)
                                .request(new OnPermission() {

                                    @Override
                                    public void hasPermission(List<String> granted, boolean isAll) {

                                    }

                                    @Override
                                    public void noPermission(List<String> denied, boolean quick) {

                                    }
                                });
                    }
                }
                break;
            case R.id.certification_img_dlt_front:
                frondurl="";
                img_dlt_front.setVisibility(View.GONE);
                Glide.with(mContext).load(imageTranslateUri(R.mipmap.ic_img_add)).into(img_card_frond);
                break;
            case R.id.certification_img_dlt_reverse:
                reverseurl="";
                img_dlt_reverse.setVisibility(View.GONE);
                Glide.with(mContext).load(imageTranslateUri(R.mipmap.ic_img_add)).into(img_card_reverse);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==-1){
            if (requestCode==FRONT_CODE){
                List<Uri> uris = Matisse.obtainResult(data);
                if (uris.size()>0) {
                    Glide.with(this).load(uris.get(0)).into(img_card_frond);
                    img_dlt_front.setVisibility(View.VISIBLE);
                    List<String> strings = Matisse.obtainPathResult(data);
                    File file = new File(strings.get(0));//实例化数据库文件
                    OkGo.<String>post(RequstUrl.URL.UploadImg)
                            .params("img",file)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                                    UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                                    frondurl = DataBean.getUrl();
                                }
                            });

                }
            }else if (requestCode==REVERSE_CODE){
                List<Uri> uris = Matisse.obtainResult(data);
                if (uris.size()>0) {
                    Glide.with(this).load(uris.get(0)).into(img_card_reverse);
                    img_dlt_reverse.setVisibility(View.VISIBLE);
                    List<String> strings = Matisse.obtainPathResult(data);
                    File file = new File(strings.get(0));//实例化数据库文件
                    OkGo.<String>post(RequstUrl.URL.UploadImg)
                            .params("img",file)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                                    UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                                    reverseurl = DataBean.getUrl();
                                    img_dlt_reverse.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String msg, CertificationBean.DataBean dataBean){
       LoadingDialogClose();
        if (code==1){
            int audit = dataBean.getAudit();
            if (audit==0){
                edt_username.setEnabled(true);
                edt_car_number.setEnabled(true);
                img_card_frond.setClickable(true);
                img_card_reverse.setClickable(true);
                rl_TypeTwo.setVisibility(View.GONE);
                btn_submit.setVisibility(View.VISIBLE);
                rl_TypeOne.setVisibility(View.VISIBLE);
                img_dlt_front.setVisibility(View.GONE);
                img_dlt_reverse.setVisibility(View.GONE);
            }else if (audit==1){
                rl_TypeOne.setVisibility(View.GONE);
                rl_TypeTwo.setVisibility(View.VISIBLE);
            }else if (audit==2){
                edt_username.setEnabled(false);
                edt_car_number.setEnabled(false);
                img_card_frond.setClickable(false);
                img_card_reverse.setClickable(false);
                img_dlt_front.setVisibility(View.GONE);
                img_dlt_reverse.setVisibility(View.GONE);
                rl_TypeTwo.setVisibility(View.GONE);
                btn_submit.setVisibility(View.GONE);
                rl_TypeOne.setVisibility(View.VISIBLE);
                img_card_frond.setVisibility(View.VISIBLE);
                img_card_reverse.setVisibility(View.VISIBLE);
                CertificationBean.DataBean.InfoBean info = dataBean.getInfo();
                edt_car_number.setText(info.getCard_num());
                edt_username.setText(info.getReal_name());
                frondurl=info.getCard_front();
                reverseurl=info.getCard_reverse();
                Glide.with(mContext).load(RequstUrl.URL.HOST+info.getCard_front()).placeholder(R.mipmap.ic_img_add).into(img_card_frond);
                Glide.with(mContext).load(RequstUrl.URL.HOST+info.getCard_reverse()).placeholder(R.mipmap.ic_img_add).into(img_card_reverse);
            }else if (audit==3){
                edt_username.setEnabled(true);
                edt_car_number.setEnabled(true);
                img_card_frond.setClickable(true);
                img_card_reverse.setClickable(true);
                rl_TypeTwo.setVisibility(View.GONE);
                btn_submit.setVisibility(View.VISIBLE);
                rl_TypeOne.setVisibility(View.VISIBLE);
                rl_audit.setVisibility(View.VISIBLE);
                img_dlt_front.setVisibility(View.VISIBLE);
                img_dlt_reverse.setVisibility(View.VISIBLE);
            }
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
        ToastUtils.showToast(mContext,msg);
    }

    public void UpdateUI(int code,String msg){
       LoadingDialogClose();
     if (code==1){
       finish();
         ToastUtils.showToast(mContext,msg);
     }else if (code ==10001){
         ToastUtils.showToast(mContext,msg);
         startActivity(new Intent(mContext,LoginActivity.class));
         finish();
     }else {
         ToastUtils.showToast(mContext,msg);
     }
    }

    private String imageTranslateUri(int resId) {

        Resources r = getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri.toString();
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
