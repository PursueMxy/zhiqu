package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.custview.CircleImageView;
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


/**
 * 手机号注册成功打开页面
 */
public class EditPersonalActivity extends BaseActivity {

    @BindView(R.id.edt_personal_img_avatar)
    CircleImageView img_avatar;
    @BindView(R.id.edt_personal_edt_nickname)
    EditText edt_nickname;
    private static final int REQUEST_CODE=2001;
    private String url;
    private String token;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal);
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
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.edit_personal_img_back,R.id.logon_btn_agree,R.id.edt_personal_img_avatar})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.edit_personal_img_back:
                finish();
                break;
            case R.id.logon_btn_agree:
                String nickname= edt_nickname.getText().toString();
                if (!nickname.equals("")) {
                    if (!url.equals("")) {
                        OkGo.<String>post(RequstUrl.URL.setUsername)
                                .params("token",token)
                                .params("username",nickname)
                                .params("avatar",url)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                        ToastUtils.showToast(mContext,resultBean.getMsg());
                                        if (resultBean.getCode()==1){
                                         startActivity(new Intent(mContext,HomeActivity.class));
                                        }else if (resultBean.getCode() ==10001){
                                            ToastUtils.showToast(mContext,resultBean.getMsg());
                                            startActivity(new Intent(mContext,LoginActivity.class));
                                            finish();
                                        }
                                    }
                                });
                    }else {
                        ToastUtils.showToast(mContext,"头像不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"昵称不能为空");
                }
                break;
            case R.id.edt_personal_img_avatar:
                try {
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
                }catch (Exception e){
                    //权限申请
                    ToastUtils.showToast(mContext,"请允许权限");
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
}
