package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.AddResumeBean;
import com.icarexm.zhiquwang.custview.Wheel.ScreenInfo;
import com.icarexm.zhiquwang.custview.Wheel.WheelMain;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.Calendar;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWorkHistoryActivity extends BaseActivity {

    @BindView(R.id.add_work_history_edt_job_content)
    EditText edt_job_content;
    @BindView(R.id.add_work_history_edt_job_cause)
    EditText edt_job_cause;
    @BindView(R.id.add_work_history_tv_job_start)
    TextView tv_job_start;
    @BindView(R.id.add_work_history_tv_job_end)
    TextView tv_job_end;
    @BindView(R.id.add_work_history_edt_company_name)
    EditText edt_company_name;
    private Context mContext;
    private int  ADDWORKHISTORY=5120;
    private View timepickerview;
    private String type;
    private String position="00000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work_history);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        ButterKnife.bind(this);
        try {
            if (type.equals("update")) {
                String company_name = intent.getStringExtra("company_name");
                String job_content = intent.getStringExtra("job_content");
                String job_start = intent.getStringExtra("job_start");
                String job_end = intent.getStringExtra("job_end");
                String leave_cause = intent.getStringExtra("leave_cause");
                position = intent.getStringExtra("position");
                edt_company_name.setText(company_name);
                edt_job_cause.setText(leave_cause);
                edt_job_content.setText(job_content);
                tv_job_end.setText(job_end);
                tv_job_start.setText(job_start);
                Log.e("position",position);
            }
        }catch (Exception e){

        }
    }

    @OnClick({R.id.add_work_history_img_back,R.id.add_work_history_btn_confirm,R.id.add_work_history_tv_job_start,R.id.add_work_history_tv_job_end})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_work_history_img_back:
                finish();
                break;
            case R.id.add_work_history_btn_confirm:
                String company_name = edt_company_name.getText().toString();
                String job_content = edt_job_content.getText().toString();
                String job_cause = edt_job_cause.getText().toString();
                String job_start= tv_job_start.getText().toString();
                String job_end= tv_job_end.getText().toString();
                if (!company_name.equals("")){
                    if (!job_content.equals("")){
                        if (!job_start.equals("")){
                            if (!job_end.equals("")){
                                AddResumeBean addResumeBean = new AddResumeBean(company_name, job_start, job_end, job_content, job_cause);
                                String s1 = new GsonBuilder().create().toJson(addResumeBean);
                                Intent intent = new Intent(mContext, MyResumeActivity.class);
                                if (type.equals("update")){
                                    intent.putExtra("position",position);
                                }else {
                                    intent.putExtra("position","00000");
                                }
                                intent.putExtra("bean",s1);
                                setResult(ADDWORKHISTORY,intent);
                                finish();
                            }else {
                                ToastUtils.showToast(mContext,"结束时间不能为空");
                            }
                        }else {
                            ToastUtils.showToast(mContext,"开始时间不能为空");
                        }
                    }else {
                        ToastUtils.showToast(mContext,"工作内容不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"公司名称不能为空");
                }
                break;
            case R.id.add_work_history_tv_job_start:
                timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain = new WheelMain(timepickerview,false);
                wheelMain.setSTART_YEAR(1900);
                ScreenInfo screenInfo = new ScreenInfo(AddWorkHistoryActivity.this);
                wheelMain.screenheight = screenInfo.getHeight();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month= calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wheelMain.setEND_YEAR(year);
                wheelMain.initDateTimePicker(year, month, day,0,0);
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddWorkHistoryActivity.this)
                        .setTitle("请选择日期")
                        .setView(timepickerview)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String time = wheelMain.getDate();
                                tv_job_start.setText(time);

                            }
                        });
                dialog.show();
                break;
            case R.id.add_work_history_tv_job_end:
                timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
                final WheelMain wheelMain1 = new WheelMain(timepickerview,false);
                wheelMain1.setSTART_YEAR(1900);
                ScreenInfo screenInfo1 = new ScreenInfo(AddWorkHistoryActivity.this);
                wheelMain1.screenheight = screenInfo1.getHeight();
                Calendar calendar1 = Calendar.getInstance();
                int year1 = calendar1.get(Calendar.YEAR);
                int month1= calendar1.get(Calendar.MONTH);
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                wheelMain1.setEND_YEAR(year1);
                wheelMain1.initDateTimePicker(year1, month1, day1,0,0);
                AlertDialog.Builder dialog1 = new AlertDialog.Builder(AddWorkHistoryActivity.this)
                        .setTitle("请选择日期")
                        .setView(timepickerview)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String time = wheelMain1.getDate();
                                tv_job_end.setText(time);
                            }
                        });
                dialog1.show();
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
