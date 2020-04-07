package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.MyApplication;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.VersionBean;
import com.icarexm.zhiquwang.contract.LoginContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.LoginPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.wxapi.WXEntryActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.login_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.login_edt_password)
    EditText edt_password;
    @BindView(R.id.login_cb)
    CheckBox login_cb;
    @BindView(R.id.login_img_avatar)
    ImageView img_avatar;

    private int AGREMEEN_CODE=1001;

    private Context mContext;
    private LoginPresenter loginPresenter;
    private String password;
    private String mobile;
    private SharedPreferences share;
    private String token;
    private String type="";
    private int versionCode;
    private String version_name;
    private CustomProgressDialog progressDialog;
    private String on_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        JPushInterface.resumePush(getApplicationContext());
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mobile = share.getString("mobile", "");
        password = share.getString("password", "");
        on_send = share.getString("on_send", "");
        loginPresenter = new LoginPresenter(this);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        edt_mobile.setText(mobile);
        edt_password.setText(password);
        //权限申请
        XXPermissions.with(this)
                .constantRequest()
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
        versionCode = getVersionCode();
        try {
           if (token!=null){
                   SharedPreferences.Editor editor = share.edit();
                   editor.putString("on_send","true");
                   editor.commit();//提交
                   startActivity(new Intent(mContext, HomeActivity.class));

           }
        }catch (Exception e){

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("token","");
        editor.putString("on_send","true");
        editor.commit();//提交
        token = share.getString("token", "");
        try {
            if (token!=null){
                if (!token.equals("")) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                }
            }
        }catch (Exception e){

        }
    }

    @OnClick({R.id.login_tv_create_account,R.id.login_tv_no_password,R.id.login_btn_start,R.id.login_tv_user_agreement
    ,R.id.login_img_avatar,R.id.login_img_wechat})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.login_tv_create_account:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_tv_create_account)) {
                    Intent intent1 = new Intent(mContext, LogonActivity.class);
                    intent1.putExtra("type", "logon");
                    startActivity(intent1);
                }
                break;
            case R.id.login_tv_no_password:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_tv_no_password)) {
                    startActivity(new Intent(mContext, ResetPasswordActivity.class));
                }
                break;
            case R.id.login_btn_start:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_btn_start)) {
                    mobile = edt_mobile.getText().toString();
                    password = edt_password.getText().toString();
                    if (login_cb.isChecked()) {
                        if (!mobile.equals("")) {
                            if (!password.equals("")) {
                                loginPresenter.GetLogin(mobile, password);
                            } else {
                                ToastUtils.showToast(mContext, "密码不能为空");
                            }
                        } else {
                            ToastUtils.showToast(mContext, "手机号不能为空");
                        }
                    } else {
                        ToastUtils.showToast(mContext, "请先同意用户协议");
                    }
                }
                break;
            case R.id.login_tv_user_agreement:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_tv_user_agreement)) {
                    Intent intent = new Intent(mContext, UserAgreementActivity.class);
                    startActivityForResult(intent, AGREMEEN_CODE);
                }
                break;
            case R.id.login_img_avatar:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_img_avatar)) {
                    startActivity(new Intent(mContext, EditPersonalActivity.class));
                }
                break;
            case R.id.login_img_wechat:
                if (!ButtonUtils.isFastDoubleClick(R.id.login_img_wechat)) {
                    WXEntryActivity.loginWeixin(mContext, MyApplication.iwxapi);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==AGREMEEN_CODE){
           login_cb.setChecked(true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }




    //登录数据返回
    public void Update(int code,String msg,String data,int other){
        if (code==1){
            startActivity(new Intent(mContext,HomeActivity.class));
            SharedPreferences.Editor editor = share.edit();
            editor.putString("mobile",mobile);
            editor.putString("password",password);
            editor.putString("token",data);
            if (on_send.equals("")) {
                editor.putString("on_send", "true");
            }
            editor.commit();//提交
            // 调用 JPush 接口来设置别名。
            setAlias(other);
        }else if (code==10002){
            startActivity(new Intent(mContext,EditPersonalActivity.class));
            SharedPreferences.Editor editor = share.edit();
            editor.putString("mobile",mobile);
            editor.putString("password",password);
            editor.putString("token",data);
            editor.putString("on_send","true");
            editor.commit();//提交
            setAlias(other);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
        else {
            ToastUtils.showToast(mContext,msg);
        }
    }

    //微信
    public void WechatLoginUpdateUI(int code, String msg, String openid){
        if (code==10004){
            Intent intent = new Intent(mContext, LogonActivity.class);
            intent.putExtra("type","wechat");
            intent.putExtra("openid",openid);
            startActivity(intent);
            finish();
        }else if (code==1){
            startActivity(new Intent(mContext,HomeActivity.class));
        }
        ToastUtils.showToast(mContext,msg);
    }
    /**
     * Jpush设置
     */
    /*
     * 绑定别名
     */
    private static final int GET_MEG_SUG=0;
    private String TAG="LoginActivity";
    private static final int MSG_SET_ALIAS=1001;
    private void setAlias(int userid) {
        String alias = String.valueOf("alias_"+userid);
        // 调用JPush API设置Alias
        mHandler1.sendMessage(mHandler1.obtainMessage(MSG_SET_ALIAS, alias));
        Log.d(TAG, "设置Jpush推送的别名alias=" + alias);
    }


    private final Handler mHandler1 = new Handler() {//专门用了一个Handler对象处理别名的注册问题
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "设置激光推送的别名-mHandler1");
            JPushInterface.setAliasAndTags(getApplicationContext(),
                    (String) msg.obj, null, mAliasCallback);
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success极光推送别名设置成功";
                    Log.i(TAG, logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    Log.i(TAG, logs);
                    mHandler1.sendMessageDelayed(mHandler1.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    Log.e(TAG, logs);
                    break;
            }
        }
    };


    /**
     * 返回版本号
     * @return
     * 非0则代表获取成功
     */
    private int getVersionCode() {
        // 1,包管理者对象packageManaer
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中，获取指定包名的基本信息（版本名称，版本号），用getPackageInfo,传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);

            return packageInfo.versionCode;//private String getVersionName()

        } catch (Exception e) {

            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名称;清单文件中
     * @return 应用版本名称 返回null代表异常
     *
     */
    private String getVersionName() {
        // 1,包管理者对象packageManaer
        PackageManager pm = getPackageManager();
        //2,从包的管理者对象中，获取指定包名的基本信息（版本名称，版本号），用getPackageInfo,传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            /**3,获取版本名称为清单文件SplashActivity1.Manifest里的 android:versionCode="1"
             *android:versionName="1.0",此方法有了返回值，将此方法返回值类型void改为String
             */
            return packageInfo.versionName;//private String getVersionName()

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
     @Override
    public void onPause() {
        super.onPause();
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

