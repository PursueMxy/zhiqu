package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_information);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.base_information_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.base_information_img_back:
                finish();
                break;
        }
    }
}
