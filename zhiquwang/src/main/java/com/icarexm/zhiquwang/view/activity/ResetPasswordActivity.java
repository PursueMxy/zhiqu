package com.icarexm.zhiquwang.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.ResetPasswordContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.ResetPasswordPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ResetPasswordActivity extends BaseActivity implements ResetPasswordContract.View {

    @BindView(R.id.reset_psw_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.reset_psw_edt_code)
    EditText edt_code;
    @BindView(R.id.reset_psw_edt_password)
    EditText edt_password;
    @BindView(R.id.reset_psw_edt_repassword)
    EditText edt_repassword;
    @BindView(R.id.reset_psw_tv_mobileCode)
    TextView tv_mobileCode;

    private Context mContext;
    private String token;
    private String mobile;
    private int Timesecond;
    private ResetPasswordPresenter resetPasswordPresenter;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        resetPasswordPresenter = new ResetPasswordPresenter(this);
    }
    @OnClick({R.id.reset_psw_img_back,R.id.reset_psw_btn_start,R.id.reset_psw_tv_mobileCode})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.reset_psw_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.reset_psw_img_back)) {
                    finish();
                }
                break;
            case R.id.reset_psw_btn_start:
                if (!ButtonUtils.isFastDoubleClick(R.id.reset_psw_btn_start)) {
                    String code = edt_code.getText().toString();
                    String password = edt_password.getText().toString();
                    String repassword = edt_repassword.getText().toString();
                    if (!mobile.equals("") && mobile.length() == 11) {
                        if (!code.equals("")) {
                            if (!password.equals("")) {
                                if (repassword.equals(password)) {
                                    resetPasswordPresenter.GetFindPass(mobile, code, password, repassword);
                                } else {
                                    ToastUtils.showToast(mContext, "两次密码输入不一致");
                                }
                            } else {
                                ToastUtils.showToast(mContext, "密码不能为空");
                            }
                        } else {
                            ToastUtils.showToast(mContext, "验证码不能为空");
                        }
                    } else {
                        ToastUtils.showToast(mContext, "手机号码不能为空");
                    }
                }
                break;
            case R.id.reset_psw_tv_mobileCode:
                if (!ButtonUtils.isFastDoubleClick(R.id.reset_psw_tv_mobileCode)) {
                    mobile = edt_mobile.getText().toString();
                    if (!mobile.equals("") && mobile.length() == 11) {
                        Timesecond = 59;
                        timeHandler.postDelayed(timeRunnable, 1000);
                        tv_mobileCode.setClickable(false);
                        resetPasswordPresenter.GetSendMsg(mobile, "2");
                    } else {
                        ToastUtils.showToast(mContext, "手机号码不能为空");
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

    //防止多次点击获取验证码
    Handler timeHandler=new Handler();
    Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
                if (Timesecond == 0) {
                    timeHandler.removeCallbacks(timeRunnable);
                    tv_mobileCode.setClickable(true);
                    tv_mobileCode.setText("发送验证码");
                } else {
                    timeHandler.postDelayed(timeRunnable, 1000);
                    tv_mobileCode.setText(Timesecond + "秒后");
                }

            Timesecond = Timesecond -1;
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code,String msg){
     if (code==1){
         ToastUtils.showToast(mContext,msg);
         startActivity(new Intent(mContext,LoginActivity.class));
         finish();
     }else if (code ==10001){
         ToastUtils.showToast(mContext,msg);
         startActivity(new Intent(mContext,LoginActivity.class));
         finish();
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
}
