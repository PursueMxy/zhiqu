package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.icarexm.zhiquwang.MyApplication;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.LoginContract;
import com.icarexm.zhiquwang.presenter.LoginPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.wxapi.WXEntryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = getIntent();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mobile = share.getString("mobile", "");
        password = share.getString("password", "");
        loginPresenter = new LoginPresenter(this);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        edt_mobile.setText(mobile);
        edt_password.setText(password);
        try {
            type = intent.getStringExtra("type");
            if (type.equals("wechat")) {
                String code = intent.getStringExtra("code");
                Log.e("微信code", code);
            }
        }catch (Exception e){}
    }


    @OnClick({R.id.login_tv_create_account,R.id.login_tv_no_password,R.id.login_btn_start,R.id.login_tv_user_agreement
    ,R.id.login_img_avatar,R.id.login_img_wechat})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.login_tv_create_account:
                startActivity(new Intent(mContext,LogonActivity.class));
                break;
            case R.id.login_tv_no_password:
                startActivity(new Intent(mContext,ResetPasswordActivity.class));
                break;
            case R.id.login_btn_start:
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
                }else {
                    ToastUtils.showToast(mContext, "请先同意用户协议");
                }
                break;
            case R.id.login_tv_user_agreement:
                Intent intent = new Intent(mContext, UserAgreementActivity.class);
                startActivityForResult(intent,AGREMEEN_CODE);
                break;
            case R.id.login_img_avatar:
                startActivity(new Intent(mContext,EditPersonalActivity.class));
                break;
            case R.id.login_img_wechat:
                WXEntryActivity.loginWeixin(mContext, MyApplication.iwxapi);
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
    public void Update(int code,String msg,String data){
        if (code==1){
            startActivity(new Intent(mContext,HomeActivity.class));
            SharedPreferences.Editor editor = share.edit();
            editor.putString("mobile",mobile);
            editor.putString("password",password);
            editor.putString("token",data);
            editor.commit();//提交
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}

