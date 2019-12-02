package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.contract.LogonContract;
import com.icarexm.zhiquwang.presenter.LogonPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogonActivity extends BaseActivity implements LogonContract.View {
   @BindView(R.id.logon_edt_mobile)
    EditText edt_mobile;
   @BindView(R.id.logon_edt_password)
   EditText edt_password;
   @BindView(R.id.logon_edt_repassword)
   EditText edt_repassword;
   @BindView(R.id.logon_edt_code)
   EditText edt_code;
   @BindView(R.id.logon_tv_mobileCode)
    TextView tv_mobileCode;
   @BindView(R.id.logon_btn_start)
    Button btn_start;
   @BindView(R.id.logon_tv_password)
   TextView tv_password;
   @BindView(R.id.logon_tv_repassword)
   TextView tv_repassword;
   @BindView(R.id.logon_password_driver)
   View logon_driver;
    @BindView(R.id.logon_repassword_driver)
    View logon_redriver;
    private Context mContext;
    private int Timesecond;
    private String mobile;
    private LogonPresenter logonPresenter;
    private String type;
    private String openid="";
    private boolean isInput=false;
    private SharedPreferences share;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        password = share.getString("password", "");
        ButterKnife.bind(this);
        if (type.equals("wechat")){
            openid = intent.getStringExtra("openid");
            btn_start.setText("绑定");
            tv_password.setVisibility(View.GONE);
            tv_repassword.setVisibility(View.GONE);
            logon_driver.setVisibility(View.GONE);
            logon_redriver.setVisibility(View.GONE);
            edt_password.setVisibility(View.GONE);
            edt_repassword.setVisibility(View.GONE);
        }else if (type.equals("logon")){
            tv_password.setVisibility(View.VISIBLE);
            tv_repassword.setVisibility(View.VISIBLE);
            logon_driver.setVisibility(View.VISIBLE);
            logon_redriver.setVisibility(View.VISIBLE);
            edt_password.setVisibility(View.VISIBLE);
            edt_repassword.setVisibility(View.VISIBLE);
            btn_start.setText("注册");
        }
        InitUI();
        logonPresenter = new LogonPresenter(this);
    }

    private void InitUI() {
        edt_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (type.equals("wechat"))
                 if (charSequence.toString().length()==11){
                     GetIsexists(charSequence.toString());
                 }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void GetIsexists(String toString) {
        OkGo.<String>post(RequstUrl.URL.is_exists)
                .params("mobile",toString)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(response.body(), PublicCodeBean.class);
                        if (publicCodeBean.getCode()==1){
                            tv_password.setVisibility(View.VISIBLE);
                            tv_repassword.setVisibility(View.VISIBLE);
                            logon_driver.setVisibility(View.VISIBLE);
                            logon_redriver.setVisibility(View.VISIBLE);
                            edt_password.setVisibility(View.VISIBLE);
                            edt_repassword.setVisibility(View.VISIBLE);
                        }else if(publicCodeBean.getCode()==2){
                            isInput=true;
                        }
                    }
                });

    }


    @OnClick({R.id.logon_img_back,R.id.logon_btn_start,R.id.logon_tv_mobileCode})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.logon_img_back:
                finish();
                break;
            case R.id.logon_btn_start:
                mobile = edt_mobile.getText().toString();
                String code= edt_code.getText().toString();
                password = edt_password.getText().toString();
                String repassword = edt_repassword.getText().toString();
                if (type.equals("wechat")){
                    if (!mobile.equals("")&& mobile.length()==11){
                        if(!code.equals("")){
                            if (isInput){
                                logonPresenter.GetBindMobile(mobile,code, password,repassword,openid);
                            }else {
                                if (!password.equals("")) {
                                    if (repassword.equals(password)) {
                                        logonPresenter.GetBindMobile(mobile,code, password,repassword,openid);
                                    } else {
                                        ToastUtils.showToast(mContext, "两次密码输入不一致");
                                    }
                                } else {
                                    ToastUtils.showToast(mContext, "密码不能为空");
                                }
                            }
                        }else {
                            ToastUtils.showToast(mContext,"验证码不能为空");
                        }
                    }else {
                        ToastUtils.showToast(mContext,"手机号码不能为空");
                    }
                }else if (type.equals("logon")){
                    if (!mobile.equals("")&& mobile.length()==11){
                        if(!code.equals("")){
                            if (!password.equals("")){
                                if (repassword.equals(password)){
                                    logonPresenter.GetRegister(mobile,code, password,repassword);
                                }else {
                                    ToastUtils.showToast(mContext,"两次密码输入不一致");
                                }
                            }else {
                                ToastUtils.showToast(mContext,"密码不能为空");
                            }
                        }else {
                            ToastUtils.showToast(mContext,"验证码不能为空");
                        }
                    }else {
                        ToastUtils.showToast(mContext,"手机号码不能为空");
                    }
                }
                break;
            case R.id.logon_tv_mobileCode:
                mobile = edt_mobile.getText().toString();
                if (!mobile.equals("")&& mobile.length()==11){
                    Timesecond =59;
                    timeHandler.postDelayed(timeRunnable,1000);
                    tv_mobileCode.setClickable(false);
                    if (type.equals("wechat")) {
                        logonPresenter.GetSendMsg(mobile, "3");
                    }else if (type.equals("logon")){
                        logonPresenter.GetSendMsg(mobile, "1");
                    }
                }else {
                    ToastUtils.showToast(mContext,"手机号码不能为空");
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

            Timesecond = Timesecond - 1;
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code,String msg){
        if (code==10001){
            finish();
            startActivity(new Intent(mContext,LoginActivity.class));
        } else{
            ToastUtils.showToast(mContext,msg);
        }
    }

    @Override
    public void UpdateUI(int code, String msg, String data) {
        ToastUtils.showToast(mContext,msg);
        if (code==1){
            SharedPreferences.Editor editor = share.edit();
            editor.putString("mobile",mobile);
            editor.putString("password",password);
            editor.putString("token",data);
            editor.commit();//提交
            startActivity(new Intent(mContext,HomeActivity.class));
        }
    }
}
