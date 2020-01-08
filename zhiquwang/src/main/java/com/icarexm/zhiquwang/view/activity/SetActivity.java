package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.VersionBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ClearCacheManager;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.SysUtil;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {


    @BindView(R.id.set_tv_clean_cache)
    TextView tv_clean_cache;
    @BindView(R.id.set_tv_versionName)
    TextView tv_versionName;
    private Context mContext;
    private int versionCode;
    private String versionName;
    private String version_name;
    private CustomProgressDialog progressDialog;
    private SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        versionCode = getVersionCode();
        versionName = getVersionName();
        tv_versionName.setText(versionName);
        try {
            String cache = ClearCacheManager.getTotalCacheSize(SetActivity.this);
            if(SysUtil.isNotNUll(cache)){
                tv_clean_cache.setText(cache);
            }else {
                tv_clean_cache.setText("0.0B");
            }

        } catch (Exception e) {

        }
    }

    @OnClick({R.id.set_img_back,R.id.set_rl_clean_cache,R.id.set_rl_about,R.id.set_rl_change_password,
            R.id.set_rl_contact_us,R.id.set_rl_feedback,R.id.set_rl_messageset,R.id.set_btn_out_login
    ,R.id.set_rl_check_update})
    public  void onViewClick(View view){
        switch (view.getId()) {
            case R.id.set_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_img_back)) {
                    finish();
                }
                break;
            case R.id.set_rl_clean_cache:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_clean_cache)) {
                    ClearCacheManager.clearAllCache(SetActivity.this);
                    ToastUtils.showToast(mContext, "清除成功");
                    tv_clean_cache.setText("0.0B");
                }
                break;
            case R.id.set_rl_change_password:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_change_password)) {
                    startActivity(new Intent(mContext, ChangePasswordActivity.class));
                }
                break;
            case R.id.set_rl_contact_us:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_contact_us)) {
                    startActivity(new Intent(mContext, ContactUsActivity.class));
                }
                break;
            case R.id.set_rl_feedback:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_change_password)) {
                    startActivity(new Intent(mContext, FeedBackActivity.class));
                }
                break;
            case R.id.set_rl_about:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_about)) {
                    startActivity(new Intent(mContext, AboutActivity.class));
                }
                break;
            case R.id.set_rl_messageset:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_messageset)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
                        // 进入设置系统应用权限界面
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                        return;
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
                        // 进入设置系统应用权限界面
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivity(intent);
                        return;
                    }
                }
                break;
            case R.id.set_btn_out_login:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_btn_out_login)) {
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("token","");
                    editor.commit();//提交
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                break;
            case R.id.set_rl_check_update:
                if (!ButtonUtils.isFastDoubleClick(R.id.set_rl_check_update)) {
                    InitApkVersion();
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

    private void InitApkVersion() {
        OkGo.<String>post(RequstUrl.URL.CheckAndroid)
                .params("code", versionCode)
                .execute(new StringCallback() {


                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        final VersionBean version = gson.fromJson(body, VersionBean.class);
                        if (version.getCode() == 1) {
                            VersionBean.DataBean data = version.getData();
                            version_name = data.getName();
//                            showUpdateDialog();
                            DownloadBuilder builderLodawn=AllenVersionChecker
                                    .getInstance()
                                    .downloadOnly(
                                            UIData.create()
                                                    .setDownloadUrl("http://zqw.kuaishanghd.com/android/zqw.apk")
                                                    .setTitle("功能升级")
                                                    .setContent("部分功能优化")

                                       );
//                            builderLodawn.setSilentDownload(true);
//                            builderLodawn.setShowDownloadingDialog(false);
                            builderLodawn.setApkName("zhiquwang");
                            builderLodawn.setForceRedownload(true);
                            builderLodawn.setNotificationBuilder(
                                    NotificationBuilder.create()
                                            .setRingtone(true)
                                            .setIcon(R.mipmap.ic_logo)
                                            .setTicker("职趣网")
                                            .setContentTitle("版本升级")
                                            .setContentText("部分功能优化")
                            );
                            builderLodawn.executeMission(mContext);
                        }else {
                            ToastUtils.showToast(mContext,version.getMsg());
                        }
                    }
                });
    }

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
