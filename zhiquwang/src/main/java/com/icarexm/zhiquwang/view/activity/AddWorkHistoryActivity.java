package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;

public class AddWorkHistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_history);
        ButterKnife.bind(this);
    }
}
