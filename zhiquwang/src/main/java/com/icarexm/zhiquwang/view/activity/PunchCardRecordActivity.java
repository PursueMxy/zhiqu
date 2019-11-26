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
import com.icarexm.zhiquwang.bean.RepairInfoBean;
import com.icarexm.zhiquwang.contract.PunchCardRecordContract;
import com.icarexm.zhiquwang.custview.calender.AdapterDate;
import com.icarexm.zhiquwang.custview.calender.AdapterWeek;
import com.icarexm.zhiquwang.custview.calender.DateUtil;
import com.icarexm.zhiquwang.custview.calender.InnerGridView;
import com.icarexm.zhiquwang.custview.calender.OnSignedSuccess;
import com.icarexm.zhiquwang.custview.calender.SignDate;
import com.icarexm.zhiquwang.presenter.PunchCardRecordPresenter;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PunchCardRecordActivity extends BaseActivity implements PunchCardRecordContract.View {

    @BindView(R.id.punch_card_record_gvDate)
     InnerGridView gvDate;
    @BindView(R.id.punch_card_record_gvWeek)
    InnerGridView gvWeek;
    @BindView(R.id.punch_card_record_tv_startTime)
    TextView tv_startTime;
    @BindView(R.id.punch_card_record_tv_stopTime)
    TextView tv_stopTime;
    @BindView(R.id.punch_card_record_tv_time)
    TextView tv_time;
    private Context mContext;
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();
    private PunchCardRecordPresenter punchCardRecordPresenter;
    private SharedPreferences share;
    private String token;
    private List<RepairInfoBean.DataBean.MonthBean> monthList=new ArrayList<>();
    private AdapterDate adapterDate;
    private int today = 1;
    private String todayDate;
    private String week;
    private String todayHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch_card_record);
        ButterKnife.bind(this);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        todayDate = DateUtils.getTodayDate();
        week = DateUtils.getWeek(todayDate);
        todayHour = DateUtils.getTodayHour();
        InitUI();
        punchCardRecordPresenter = new PunchCardRecordPresenter(this);
        punchCardRecordPresenter.GetRepairInfo(token);

    }

    private void InitUI() {
        tv_time.setText(todayDate+" "+week);
        gvWeek.setAdapter(new AdapterWeek(mContext));
        adapterDate = new AdapterDate(mContext);
        gvDate.setAdapter(adapterDate);
    }

    @OnClick({R.id.punch_card_record_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.punch_card_record_img_back:
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
                    if (status.get(i)) {
                        Toast.makeText(context, "重复选择", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int a = 0; a < status.size(); a++) {
                            status.set(a, false);
                            Boolean aBoolean = status.get(i);
                        }
                        status.set(i, true);
                        notifyDataSetChanged();
                        if (onSignedSuccess != null) {
                            onSignedSuccess.OnSignedSuccess();
                        }
                        Integer integer = days.get(i);
                        try {
                            if (today>integer-1) {
                                UpdateSlt(integer);
                            }
                        }catch (Exception e){}
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
            UpdateSlt(today);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }

    }

    public void UpdateSlt(int days){
        String start_time = monthList.get(days-1).getStart_time();
        String end_time = monthList.get(days-1).getEnd_time();
        if (start_time.equals("")){
            tv_startTime.setText("缺卡");
        }else {
            tv_startTime.setText("打卡时间" + start_time + "（上班打卡）");
        }
        if (end_time.equals("")){
            tv_stopTime.setText("缺卡");
        }else {
            tv_stopTime.setText("打卡时间" + end_time + "（下班打卡）");
        }
    }
}
