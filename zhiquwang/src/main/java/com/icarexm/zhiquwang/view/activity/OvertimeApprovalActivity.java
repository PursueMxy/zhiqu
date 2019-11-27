package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.OvertimeApproverBean;
import com.icarexm.zhiquwang.bean.RepairInfoBean;
import com.icarexm.zhiquwang.contract.OvertimeApprovalContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.calender.AdapterWeek;
import com.icarexm.zhiquwang.custview.calender.DateUtil;
import com.icarexm.zhiquwang.custview.calender.InnerGridView;
import com.icarexm.zhiquwang.custview.calender.OnSignedSuccess;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.presenter.OvertimeApprovalPresenter;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OvertimeApprovalActivity extends BaseActivity implements OvertimeApprovalContract.View {

    private  int today;
    @BindView(R.id.overtime_approval_rl_festival)
    RelativeLayout rl_overtime_mode;
    @BindView(R.id.overtime_approval_tv_festival)
    TextView tv_festival;
    @BindView(R.id.overtime_approval_gvDate)
    InnerGridView gvDate;
    @BindView(R.id.overtime_approval_gvWeek)
    InnerGridView gvWeek;
    @BindView(R.id.overtime_approval_tv_time)
    TextView tv_time;
    @BindView(R.id.overtime_approval_tv_classes)
    TextView tv_classes;
    @BindView(R.id.overtime_approval_tv_hours)
    TextView tv_hours;
     private String TypeOfWork="1";
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();

    private List<OvertimeApproverBean.DataBean.MonthBean> monthList=new ArrayList<>();
    private String todayDate;
    private String week;
    private String todayHour;
    private Context mContext;
    private String token;
    private AdapterDate adapterDate;
    private OvertimeApprovalPresenter overtimeApprovalPresenter;
    private String CLASSES;
    private String Festival;
    private List<String> Hours_List=new ArrayList<>();
    private String overtime_hours;
    private int todays;
    private List<OvertimeApproverBean.DataBean.FestivalBean> festival_list=new ArrayList<>();
    private List<String> CLASSES_LIST=new ArrayList<>();
    private int festival_id;
    private List<OvertimeApproverBean.DataBean.ClassesBean> classes_list=new ArrayList<>();
    private List<String> Festival_List=new ArrayList<>();
    private int classes_id;
    private Integer SltDay;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime_approval);
        ButterKnife.bind(this);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        todayDate = DateUtils.getTodayDate();
        week = DateUtils.getWeek(todayDate);
        todayHour = DateUtils.getTodayHour();
        mContext = getApplicationContext();
        Intent intent = getIntent();
        TypeOfWork=intent.getStringExtra("TypeOfWork");
        InitHours();
        InitUI();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        todays=Integer.parseInt(DateUtil.getToday());
        SltDay=todays;
        overtimeApprovalPresenter = new OvertimeApprovalPresenter(this);
        overtimeApprovalPresenter.GetOertimeRecords(token,TypeOfWork);
    }

    private void InitHours() {
        for (int a=0;a<31;a++){
            Hours_List.add(a*0.5+"");
        }
    }


    private void InitUI() {
        tv_time.setText(todayDate +" "+ week);
        gvWeek.setAdapter(new AdapterWeek(mContext));
        adapterDate = new AdapterDate(mContext);
        gvDate.setAdapter(adapterDate);
        if (TypeOfWork.equals("1")){
            rl_overtime_mode.setVisibility(View.VISIBLE);
        }else {
            rl_overtime_mode.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.overtime_approval_img_back,R.id.overtime_approval_rl_classes,R.id.overtime_approval_rl_festival,
            R.id.overtime_approval_rl_hours,R.id.overtime_approval_btn_confirms})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.overtime_approval_img_back:
                finish();
                break;
            case R.id.overtime_approval_rl_classes:
                ClassesDialog();
                break;
            case R.id.overtime_approval_rl_festival:
                FestivalDialog();
                break;
            case R.id.overtime_approval_rl_hours:
                HoursDialog();
                break;
            case R.id.overtime_approval_btn_confirms:
                overtimeApprovalPresenter.GetdoRecords(token,TypeOfWork, classes_id+"",festival_id+"",overtime_hours,SltDay+"");
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class AdapterDate extends BaseAdapter {

        private Context context;


        //签到状态，实际应用中初始化签到状态可通过该字段传递
        private OnSignedSuccess onSignedSuccess;
        private final int maxDay;
        //签到成功的回调方法，相应的可自行添加签到失败时的回调方法
        public AdapterDate(Context context) {
            this.context = context;
            today = Integer.parseInt(DateUtil.getToday());
            //获取当月天数
            maxDay = DateUtil.getCurrentMonthLastDay();
            for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
                //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
                days.add(0);
                //0代表需要隐藏的item
                status.add(false);
                //false代表为签到状态
            }
            for (int i = 0; i < maxDay; i++) {
                days.add(i + 1);
                //初始化日历数据
                status.add(false);
                //初始化日历签到状态
            }
        }

        @Override
        public int getCount() {
            return days.size();
        }

        @Override
        public Object getItem(int i) {
            return days.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
           ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_day, null);
                viewHolder = new ViewHolder();
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.tv = view.findViewById(R.id.tvWeek);
            viewHolder.rlItem = view.findViewById(R.id.rlItem);
            viewHolder.img_to_card_week = view.findViewById(R.id.item_to_card_week);
            viewHolder.tv.setText(days.get(i) + "");
            viewHolder.img_to_card_week.setVisibility(View.GONE);
            if (days.get(i) == 0) {
                viewHolder.rlItem.setVisibility(View.GONE);
            }
            if (status.get(i)) {
                viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.tv.setBackgroundResource(R.drawable.bg_green_22);
            } else {
                if (days.get(i) == today) {
                    viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                    viewHolder.tv.setBackgroundResource(R.drawable.bg_today_22);
                } else {
                    viewHolder.tv.setTextColor(Color.parseColor("#666666"));
                    viewHolder.tv.setBackgroundColor(context.getResources().getColor(R.color.white));
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer  integer = days.get(i);
                    if (monthList.size()+1> integer &&monthList.size()>1) {
                        SltDay =integer;
                        if (status.get(i)) {
                            Toast.makeText(context, "重复选择", Toast.LENGTH_SHORT).show();
                        } else {
                            for (int a = 0; a < status.size(); a++) {
                                status.set(a, false);
                            }
                            status.set(i, true);
                            notifyDataSetChanged();
                            if (onSignedSuccess != null) {
                                onSignedSuccess.OnSignedSuccess();
                            }
                        }
                        String classes_id = monthList.get(integer-1).getClasses_id();
                        String festival_id = monthList.get(integer-1).getFestival_id();
                        overtime_hours = monthList.get(integer-1).getHours();
                        UpdateUI( festival_id,classes_id,overtime_hours);

                    }else {
                        ToastUtils.showToast(mContext,"超出当前时间");
                    }
                }
            });
            return view;
        }

        class ViewHolder {
            RelativeLayout rlItem;
            TextView tv;
            ImageView img_to_card_week;
        }

        public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess) {
            this.onSignedSuccess = onSignedSuccess;
        }
    }

    public void UpdateUI(String classes_id,String festival_id,String hours){
        tv_hours.setText(hours+"小时");
        tv_classes.setText(classes_id);
        tv_festival.setText(festival_id);
    }

    public void UpdateUI(int code,String msg){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext, msg);
        }
    }

    //班次
    private void ClassesDialog() {
        classes_id = classes_list.get(0).getClasses_id();
        tv_classes.setText(classes_list.get(0).getClasses_name());
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("班次");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(CLASSES_LIST,0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                CLASSES =item;
                classes_id = classes_list.get(selectedIndex).getClasses_id();
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
                tv_classes.setText(CLASSES);
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

    //加班类型
    private void FestivalDialog(){
        festival_id = festival_list.get(0).getFestival_id();
        tv_festival.setText(festival_list.get(0).getFestival_name());
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("加班模式");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Festival_List,0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                Festival =item;
                festival_id = festival_list.get(selectedIndex).getFestival_id();
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
                tv_festival.setText(Festival);
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

    //加班时长
    private void HoursDialog() {
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("班次");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Hours_List,0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
               overtime_hours =item;
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
                tv_hours.setText(overtime_hours+"小时");
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

    public void UpdateUI(int code, String msg, OvertimeApproverBean.DataBean data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1) {
            List<OvertimeApproverBean.DataBean.MonthBean> month = data.getMonth();
            festival_list = data.getFestival();
            for (int a = 0; a < festival_list.size(); a++) {
                Festival_List.add(festival_list.get(a).getFestival_name());
            }
            classes_list = data.getClasses();
            for (int b = 0; b < classes_list.size(); b++) {
                CLASSES_LIST.add(classes_list.get(b).getClasses_name());
            }
            monthList = month;
            adapterDate.notifyDataSetChanged();
        }if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
        ToastUtils.showToast(mContext,msg);
    }
}
