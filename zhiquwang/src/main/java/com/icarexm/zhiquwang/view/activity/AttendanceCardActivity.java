package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

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
}
