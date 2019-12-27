package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.LabelsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryEnterpriseActivity extends BaseActivity {

    @BindView(R.id.entry_enterprise_tv_name)
    TextView tv_name;
    @BindView(R.id.entry_enterprise_tv_position)
    TextView tv_position;
    @BindView(R.id.entry_enterprise_tv_age)
    TextView tv_age;
    @BindView(R.id.entry_enterprise_tv_address)
    TextView tv_address;
    @BindView(R.id.entry_enterprise_tv_today)
    TextView tv_today;
    @BindView(R.id.entry_enterprise_tv_number_day)
    TextView tv_number_day;
    @BindView(R.id.entry_enterprise_tv_salary)
    TextView tv_salary;
    @BindView(R.id.entry_enterprise_labels)
    LabelsView labels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_enterprise);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.entry_enterprise_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.entry_enterprise_img_back:
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
