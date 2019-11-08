package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWorkHistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_history);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_work_history_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_work_history_img_back:
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
