package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceCardActivity extends BaseActivity {

    private Map<String, Calendar> map = new HashMap<>();
    private Context mContext;
    private Calendar calendars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_card);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        initData();
        InitUI();
    }

    private void InitUI() {
    }

    private void initData() {
    }

    @OnClick({R.id.attendance_card_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.attendance_card_img_back:
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
