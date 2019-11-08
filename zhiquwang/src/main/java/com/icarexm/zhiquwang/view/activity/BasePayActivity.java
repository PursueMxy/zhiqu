package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasePayActivity extends BaseActivity {

     @BindView(R.id.base_pay_tv_basic_salary)
    TextView tv_basic_salary;
     private String Base_Type="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pay);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Base_Type = intent.getStringExtra("Base_Type");
        InitUI();
    }

    private void InitUI() {
     if (Base_Type.equals("1")){
         tv_basic_salary.setText("底薪设置");
     }else {
         tv_basic_salary.setText("小时工资");
     }
    }

    @OnClick({R.id.base_pay_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.base_pay_img_back:
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
