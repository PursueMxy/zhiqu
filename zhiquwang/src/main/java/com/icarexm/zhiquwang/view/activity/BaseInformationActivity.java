package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.BaseInformationPresenter;
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

public class BaseInformationActivity extends BaseActivity implements BaseInformationContract.View {

    @BindView(R.id.base_information_img_avatar)
    CircleImageView img_avatar;
    @BindView(R.id.base_information_tv_name)
    EditText tv_name;
    @BindView(R.id.base_information_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.base_information_tv_is_auth)
    TextView tv_is_auth;

    private BaseInformationPresenter baseInformationPresenter;
    private String token;
    private Context mContext;
    private static final int REQUEST_CODE=1001;
    private String url="";
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_information);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        baseInformationPresenter = new BaseInformationPresenter(this);
        baseInformationPresenter.GetBaseInfor(token);
    }
    @OnClick({R.id.base_information_img_back,R.id.base_information_img_avatar,R.id.base_information_img_edtname,R.id.base_information_btn_confirm})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.base_information_img_back:
                finish();
                break;
            case R.id.base_information_btn_confirm:
                String userName = tv_name.getText().toString();
                if (!userName.equals("")){
                    baseInformationPresenter.GetUpdateUser(token,userName,url);
                }else {
                    ToastUtils.showToast(mContext,"用户名不能为空");
                }
                break;
            case R.id.base_information_img_avatar:
                Matisse.from(this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhkj.syyj.fileprovider", "test"))
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
                        .forResult(REQUEST_CODE);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_CODE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (uris.size()>0) {
                        Glide.with(this).load(uris.get(0)).into(img_avatar);
                        List<String> strings = Matisse.obtainPathResult(data);
                        File file = new File(strings.get(0));//实例化数据库文件
                        OkGo.<String>post(RequstUrl.URL.UploadImg)
                                .params("img",file)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                                        UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                                        url = DataBean.getUrl();
                                    }
                                });
                    }
                    break;
                default:
            }
        }
    }

    public void UpdateUI(int code, String msg, BaseInforBean.DataBean data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1){
            tv_is_auth.setText(data.getIs_auth());
            tv_mobile.setText(data.getMobile());
            tv_name.setText(data.getUser_name());
            Glide.with(mContext).load(RequstUrl.URL.HOST+data.getAvatar()).into(img_avatar);
            url=data.getAvatar();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    public void UpdateUI(int code,String msg){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1){
            Intent intent = new Intent(mContext, HomeActivity.class);
            intent.putExtra("currentItems","3");
            startActivity(intent);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }

    }
}
