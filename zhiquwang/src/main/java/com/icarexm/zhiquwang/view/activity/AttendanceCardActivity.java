package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.RepairInfoBean;
import com.icarexm.zhiquwang.contract.AttendanceCardContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.calender.AdapterWeek;
import com.icarexm.zhiquwang.custview.calender.DateUtil;
import com.icarexm.zhiquwang.custview.calender.InnerGridView;
import com.icarexm.zhiquwang.custview.calender.OnSignedSuccess;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.presenter.AttendanceCardPresenter;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceCardActivity extends BaseActivity implements AttendanceCardContract.View {
    private  int today;
    @BindView(R.id.attendance_card_gvDate)
    InnerGridView gvDate;
     @BindView(R.id.attendance_card_gvWeek)
     InnerGridView gvWeek;
     @BindView(R.id.attendance_card_tv_classes_name)
     TextView tv_classes_name;
     @BindView(R.id.attendance_card_tv_startTime)
     TextView tv_startTime;
     @BindView(R.id.attendance_card_tv_stopTime)
     TextView tv_stopTime;
     @BindView(R.id.attendance_card_tv_time)
     TextView tv_time;
     @BindView(R.id.attendance_card_ll)
     LinearLayout ll_attendance_card;
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();
    private Map<String, Calendar> map = new HashMap<>();
    private Context mContext;
    private List<RepairInfoBean.DataBean.MonthBean> monthList=new ArrayList<>();
    private AdapterDate adapterDate;
    private AttendanceCardPresenter attendanceCardPresenter;
    private String token;
    private String[] CLASSES_LIST=new String[]{"白班","夜班"};
    private String CLASSES;
    private List<String> HourList=new ArrayList<>();
    private List<String> MinList=new ArrayList<>();
    private String Hour="00";
    private String Min="00";
    private TextView tv_title;
    private String todayDate;
    private String week;
    private String todayHour;
    private long start_time;
    private long stop_time;
    private int SltDay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_card);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        todayDate = DateUtils.getTodayDate();
        week = DateUtils.getWeek(todayDate);
        todayHour = DateUtils.getTodayHour();
        mContext = getApplicationContext();
        initData();
        InitUI();
        attendanceCardPresenter = new AttendanceCardPresenter(this);
        attendanceCardPresenter.GetRepairInfo(token);
    }

    private void InitUI() {
        tv_time.setText(todayDate+" "+week);
        gvWeek.setAdapter(new AdapterWeek(mContext));
        adapterDate = new AdapterDate(mContext);
        gvDate.setAdapter(adapterDate);
    }

    private void initData() {
        for(int a=0;a<24;a++){
            if (a<10){
                HourList.add("0"+a);
            }else {
                HourList.add(a+"");
            }
        }
        for (int b=0;b<60;b++){
            if (b<10){
                MinList.add("0"+b);
            }else {
                MinList.add(b+"");
            }
        }
    }

    @OnClick({R.id.attendance_card_img_back,R.id.attendance_card_rl_classes,R.id.attendance_card_rl_start,
            R.id.attendance_card_rl_stop,R.id.attendance_card_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.attendance_card_img_back:
                finish();
                break;
            case R.id.attendance_card_rl_classes:
                ClassesDialog();
                break;
            case R.id.attendance_card_rl_start:
                BirthDateDialog(0);
                break;
            case R.id.attendance_card_rl_stop:
                BirthDateDialog(1);
                break;
            case R.id.attendance_card_btn_confirm:
                String classes_name= tv_classes_name.getText().toString();
                String startTime= tv_startTime.getText().toString();
                String stopTime= tv_stopTime.getText().toString();
                int day_shift=1;
                try {
                    start_time = DateUtils.dateToStamp1(todayDate + " " + startTime);
                    stop_time = DateUtils.dateToStamp1(todayDate + " " + stopTime);
                    Log.e("start_time",start_time+"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (classes_name.equals("白班")){
                    day_shift=1;
                }else {
                    day_shift=2;
                }
                attendanceCardPresenter.GetReissue(token,day_shift+"",start_time+"",stop_time+"",SltDay+"");
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
            }else {
                Integer integer = days.get(i);
                if (monthList.size()+1>integer&&monthList.size()>1) {
                    if (monthList.get(integer-1).getIs_play() == 1) {
                        viewHolder.img_to_card_week.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.img_to_card_week.setVisibility(View.GONE);
                    }
                }
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
                     Integer integer = days.get(i);
                    if (monthList.size()+1>integer&&monthList.size()>1) {
                        if (monthList.get(integer-1).getIs_play()>1) {
                            SltDay=integer;
                            ll_attendance_card.setVisibility(View.VISIBLE);
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
                        }else {
                            ll_attendance_card.setVisibility(View.GONE);
                            ToastUtils.showToast(mContext,"已经打过卡了");
                        }
                    }else {
                        ll_attendance_card.setVisibility(View.GONE);
                        ToastUtils.showToast(mContext,"超过当前日期了");
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

    public void UpdateUI(int code, String msg, RepairInfoBean.DataBean data){
        if (code==1){
            monthList.clear();
            monthList.addAll(data.getMonth());
            adapterDate.notifyDataSetChanged();
        }
    }

    //班次
    private void ClassesDialog() {
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("班次");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Arrays.asList(CLASSES_LIST),0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                CLASSES =item;
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
                tv_classes_name.setText(CLASSES);
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

    //上下班时间
    private void BirthDateDialog(int type){
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_birth_date, null);
        tv_title = BirthDateInflate.findViewById(R.id.dialog_bottom_tv_title);
        tv_title.setText("请选择时间");
        MyWheelView groupwva_one = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_one);
        groupwva_one.setItems(HourList,0);
        MyWheelView groupwva_two = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_two);
        groupwva_two.setItems(MinList,0);
        groupwva_one.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                Hour=item;
            }
        });
        groupwva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                Min=item;
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
                if (type==0){
                 tv_startTime.setText(Hour+":"+Min);
                }else if (type==1){
                  tv_stopTime.setText(Hour+":"+Min);
                }
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

    public void UpdateUI(int code,String msg){
     if (code==1){
         attendanceCardPresenter.GetRepairInfo(token);
     }
     ToastUtils.showToast(mContext,msg);
    }
}
