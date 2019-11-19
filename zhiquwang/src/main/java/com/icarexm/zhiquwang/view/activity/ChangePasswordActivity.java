package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.ChangePasswordContract;
import com.icarexm.zhiquwang.presenter.ChangePasswordPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity implements ChangePasswordContract.View {

    @BindView(R.id.change_psw_edt_password)
    EditText edt_password;
    @BindView(R.id.change_psw_edt_newpassword)
    EditText edt_newpassword;
    @BindView(R.id.change_psw_edt_repassword)
    EditText edt_repassword;
    private String token;
    private Context mContext;
    private ChangePasswordPresenter changePasswordPresenter;
    private String newpassword;
    private SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        changePasswordPresenter = new ChangePasswordPresenter(this);
    }

    @OnClick({R.id.change_psw_img_back,R.id.change_psw_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.change_psw_img_back:
                finish();
                break;
            case R.id.change_psw_btn_confirm:
                String password= edt_password.getText().toString();
                newpassword = edt_newpassword.getText().toString();
                String repassword= edt_repassword.getText().toString();
                if (!password.equals("")){
                    if (!newpassword.equals("")){
                       if (repassword.equals(newpassword)){
                       changePasswordPresenter.GetChangePassword(token,password, newpassword,repassword);
                        }else {
                           ToastUtils.showToast(mContext,"两次密码输入不一致");
                       }
                    }else {
                        ToastUtils.showToast(mContext,"新密码不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"密码不能为空");
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

    public void UpdateUI(int code,String msg){
        if (code==1){
            SharedPreferences.Editor editor = share.edit();
            editor.putString("password",newpassword);
            editor.commit();//提交
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}
