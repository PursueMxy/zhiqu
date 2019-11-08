package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.icarexm.zhiquwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OvertimeApprovalActivity extends BaseActivity {

     @BindView(R.id.overtime_approval_rl_mode)
    RelativeLayout rl_overtime_mode;
     private String TypeOfWork="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime_approval);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        TypeOfWork=intent.getStringExtra("TypeOfWork");
        InitUI();
    }

    private void InitUI() {
        if (TypeOfWork.equals("1")){
            rl_overtime_mode.setVisibility(View.VISIBLE);
        }else {
            rl_overtime_mode.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.overtime_approval_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.overtime_approval_img_back:
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
