package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.icarexm.zhiquwang.R;

public class LunchActivity extends BaseActivity {
    private Context mContext;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(mContext,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        mContext = getApplicationContext();
        handler.postDelayed(runnable,3000);
    }
}
