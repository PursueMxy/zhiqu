package com.icarexm.zhiquwang.view.actvity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAgreementActivity extends AppCompatActivity {

    private Context mContext;
    private int AGREMEEN_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_agreement_img_back,R.id.logon_btn_agree})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.user_agreement_img_back:
                finish();
                break;
            case R.id.logon_btn_agree:
                Intent intent = new Intent(mContext, LoginActivity.class);
                setResult(AGREMEEN_CODE,intent);
                finish();
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
}
