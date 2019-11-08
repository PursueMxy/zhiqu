package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;

import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateBaseInforActivity extends BaseActivity {

     @BindView(R.id.update_base_infor_tv_education)
    TextView tv_education;
     @BindView(R.id.update_base_infor_tv_birth_date)
     TextView tv_birth_date;
     @BindView(R.id.update_base_infor_tv_sex)
     TextView tv_sex;
    private Context mContext;
    private static final String[] AGEGROUP = new String[]{"博士","硕士","本科","大专", "高中","中专","初中及以下"};
    private String ageGroupString="本科";
    private static final String[] BIRTH_MONTH=new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    private List<String> YearList=new ArrayList<>();
    private int StartYear=1900;
    private int StopYear=2020;
    private String SltYear="1990年";
    private String SltMonth="0月";
    private String[] SEX_LIST=new String[]{"男","女"};
    private String sex="男";
    private String[] SltNull=new String[]{"请先选择上级"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_base_infor);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitYear();
    }

    private void InitYear() {
        for (int a=StartYear;a<StopYear;a++){
            YearList.add(a+"年");
        }
    }

    @OnClick({R.id.update_base_infor_img_back,R.id.update_base_infor_rl_education,R.id.update_base_rl_time,R.id.update_base_rl_sex})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.update_base_infor_img_back:
                finish();
                break;
            case R.id.update_base_infor_rl_education:
                EducationDialog();
                break;
            case R.id.update_base_rl_time:
                BirthDateDialog();
                break;
            case R.id.update_base_rl_sex:
                SexDialog();
                break;
        }
    }

    private void EducationDialog() {
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View age_groupinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title = age_groupinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("学历");
        MyWheelView age_groupwva = age_groupinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Arrays.asList(AGEGROUP),2);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                ageGroupString=item;
            }
        });
        age_groupinflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_groupDialog.dismiss();
            }
        });
        age_groupinflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_education.setText(ageGroupString);
                age_groupDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (age_groupDialog !=null && age_groupDialog.isShowing()) {
            return;
        }

        age_groupDialog.setContentView(age_groupinflate);
        age_groupDialog.show();
    }


    private void BirthDateDialog(){
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_birth_date, null);
        MyWheelView groupwva_one = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_one);
        groupwva_one.setItems(YearList,90);
        MyWheelView groupwva_two = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_two);
        groupwva_two.setItems(Arrays.asList(SltNull),0);
        groupwva_one.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                SltYear=item;
                groupwva_two.setItems(Arrays.asList(BIRTH_MONTH),0);
            }
        });
        groupwva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                SltMonth=item;
            }
        });
        BirthDateInflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_groupDialog.dismiss();
            }
        });
        BirthDateInflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_birth_date.setText( SltYear+SltMonth);
                age_groupDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (age_groupDialog !=null && age_groupDialog.isShowing()) {
            return;
        }

        age_groupDialog.setContentView(BirthDateInflate);
        age_groupDialog.show();
    }

    private void SexDialog() {
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("性别");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Arrays.asList(SEX_LIST),0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                sex=item;
            }
        });
        Sexinflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SexDialog.dismiss();
            }
        });
        Sexinflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sex.setText(sex);
                SexDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (SexDialog !=null && SexDialog.isShowing()) {
            return;
        }

        SexDialog.setContentView(Sexinflate);
        SexDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
