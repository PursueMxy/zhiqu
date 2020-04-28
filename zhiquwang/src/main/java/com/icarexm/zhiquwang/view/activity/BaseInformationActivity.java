package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.BaseInformationPresenter;
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
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

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
        //权限申请
        XXPermissions.with(this)
                .permission(Permission.CAMERA,Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE,Permission.ACCESS_FINE_LOCATION,
                        Permission.ACCESS_COARSE_LOCATION,Permission.READ_CALENDAR)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        ButterKnife.bind(this);
         LoadingDialogShow();
        baseInformationPresenter = new BaseInformationPresenter(this);
        baseInformationPresenter.GetBaseInfor(token);
    }
    @OnClick({R.id.base_information_img_back,R.id.base_information_img_avatar,R.id.base_information_img_edtname,R.id.base_information_btn_confirm})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.base_information_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.base_information_img_back)) {
                    finish();
                }
                break;
            case R.id.base_information_btn_confirm:
                if (!ButtonUtils.isFastDoubleClick(R.id.base_information_btn_confirm)) {
                    String userName = tv_name.getText().toString();
                    if (!userName.equals("")) {
                        baseInformationPresenter.GetUpdateUser(token, userName, url);
                    } else {
                        ToastUtils.showToast(mContext, "用户名不能为空");
                    }
                }
                break;
            case R.id.base_information_img_avatar:
                if (!ButtonUtils.isFastDoubleClick(R.id.base_information_img_avatar)) {
                    try {
                        Matisse.from(this)
                                .choose(MimeType.ofImage(), false)
                                .countable(true)
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true, "com.icarexm.zhiquwang.fileprovider", "test"))
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_110))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR)
                                .thumbnailScale(0.5f)
                                .imageEngine(new GlideEngine())
                                .showSingleMediaType(true)
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .autoHideToolbarOnSingleTap(true)
                                .forResult(REQUEST_CODE);
                    } catch (Exception e) {
                        //权限申请
                        ToastUtils.showToast(mContext, "请允许权限");
                        XXPermissions.with(this)
                                .permission(Permission.CAMERA,Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE,Permission.ACCESS_FINE_LOCATION,
                                        Permission.ACCESS_COARSE_LOCATION,Permission.READ_CALENDAR)
                                .request(new OnPermission() {

                                             @Override
                                             public void hasPermission(List<String> granted, boolean isAll) {

                                             }

                                             @Override
                                             public void noPermission(List<String> denied, boolean quick) {

                                             }
                                         }
                                );
                    }
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
                        List<String> strings = Matisse.obtainPathResult(data);
                        File file = new File(strings.get(0));//实例化数据库文件
                        Luban.with(this)
                                .load(file)
                                .ignoreBy(100)
                                .setTargetDir(getPath())
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        UserAvatar(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();
                    }
                    break;
                default:
            }
        }
    }

    public void UpdateUI(int code, String msg, BaseInforBean.DataBean data){
      LoadingDialogClose();
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

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/zqw/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    //上传头像
    public void UserAvatar(File file){
        OkGo.<String>post(RequstUrl.URL.UploadImg)
                .params("img",file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                        UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                        url = DataBean.getUrl();
                        Glide.with(mContext).load(RequstUrl.URL.HOST+url).into(img_avatar);
                    }
                });
    }
}
