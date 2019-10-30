package com.icarexm.zhiquwang.view.actvity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_tv_create_account,R.id.login_tv_no_password,R.id.login_btn_start,R.id.login_tv_user_agreement
    ,R.id.login_img_avatar})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.login_tv_create_account:
                startActivity(new Intent(mContext,LogonActivity.class));
                break;
            case R.id.login_tv_no_password:
                startActivity(new Intent(mContext,ResetPasswordActivity.class));
                break;
            case R.id.login_btn_start:
                startActivity(new Intent(mContext,HomeActivity.class));
                String mobile = edt_mobile.getText().toString();
                String password = edt_password.getText().toString();
                if (login_cb.isChecked()) {
                    if (!mobile.equals("")) {
                        if (!password.equals("")) {
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==AGREMEEN_CODE){
           login_cb.setChecked(true);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
