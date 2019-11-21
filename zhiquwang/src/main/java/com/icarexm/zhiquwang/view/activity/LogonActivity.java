package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.LogonContract;
import com.icarexm.zhiquwang.presenter.LogonPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

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
    private Context mContext;
    private int Timesecond;
    private String mobile;
    private LogonPresenter logonPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        logonPresenter = new LogonPresenter(this);
    }
    @OnClick({R.id.logon_img_back,R.id.logon_btn_start,R.id.logon_tv_mobileCode})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.logon_img_back:
                finish();
                break;
            case R.id.logon_btn_start:
                String code= edt_code.getText().toString();
                String password = edt_password.getText().toString();
                String repassword = edt_repassword.getText().toString();
                if (!mobile.equals("")&& mobile.length()==11){
                    if(!code.equals("")){
                        if (!password.equals("")){
                            if (repassword.equals(password)){
                                logonPresenter.GetFindPass(mobile,code,password,repassword);
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
                break;
            case R.id.logon_tv_mobileCode:
                mobile = edt_mobile.getText().toString();
                if (!mobile.equals("")&& mobile.length()==11){
                    Timesecond =59;
                    timeHandler.postDelayed(timeRunnable,1000);
                    tv_mobileCode.setClickable(false);
                    logonPresenter.GetSendMsg(mobile,"2");
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
}
