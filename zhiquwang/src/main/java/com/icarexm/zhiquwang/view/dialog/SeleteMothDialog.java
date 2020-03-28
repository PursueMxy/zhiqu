package com.icarexm.zhiquwang.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.view.activity.WagesDetailActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeleteMothDialog extends Dialog {
    private Context mContext;
    private static final String[] BIRTH_MONTH=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
    private List<String> YearList=new ArrayList<>();
    private List<String> MonthList=new ArrayList<>();
    private int StartYear=2015;
    private int StopYear=2020;
    private String SltYear="2015";
    private String SltMonth="0";
    private int slt_year;
    @BindView(R.id.dialog_bottom_tv_title)
    TextView tv_title;
    @BindView(R.id.dialog_bottom_wheel_one)
    MyWheelView wheel_year;
    @BindView(R.id.dialog_bottom_wheel_two)
    MyWheelView wheel_month;
    private DateOnClickListtener dateOnClickListtener;


    public SeleteMothDialog(@NonNull Context context,int slt_year) {
        super(context);
        StopYear=DateUtils.getYearDateInt();
        this.mContext=context;
        this.slt_year=slt_year;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_birth_date);
        ButterKnife.bind(this);
        tv_title.setText("选择年月");
        getEmojiOnClickListtener();
        InitUI();
    }

    private void InitUI() {
        for (int a=StartYear;a<StopYear;a++){
            YearList.add(a+"");
        }
        for (int a=0;a<BIRTH_MONTH.length;a++){
            MonthList.add(BIRTH_MONTH[a]);
        }
        wheel_year.setItems(YearList,StopYear-slt_year);
        wheel_month.setItems(MonthList,0);
        wheel_year.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                SltYear=item;
                SltMonth="01";
            }
        });
        wheel_month.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                SltMonth=item;
            }
        });
    }

    @OnClick({R.id.dialog_bottom_img_cancel,R.id.dialog_bottom_img_opt})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.dialog_bottom_img_cancel:
                dismiss();
                break;
            case R.id.dialog_bottom_img_opt:
               dateOnClickListtener.UpdateDate(SltYear+"-"+SltMonth);
               dismiss();
                break;
        }
    }


    public DateOnClickListtener getEmojiOnClickListtener() {
        return dateOnClickListtener;
    }

    public void setDateOnClickListtener(DateOnClickListtener mDateOnClickListtener) {
        dateOnClickListtener = mDateOnClickListtener;
    }
    public interface DateOnClickListtener {
        void UpdateDate(String uid);
    }


}
