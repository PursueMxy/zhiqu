package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ClockRemindedInfoBean;
import com.icarexm.zhiquwang.bean.LiteSubsidyBean;
import com.icarexm.zhiquwang.bean.LiteWeekBean;
import com.icarexm.zhiquwang.contract.SetReminderContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.presenter.SetReminderPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetReminderActivity extends BaseActivity implements SetReminderContract.View {

    @BindView(R.id.set_reminder_rl_dialog)
    RelativeLayout rl_dialog;
    @BindView(R.id.set_reminder_list_week)
    NoScrollListView listView_week;
    @BindView(R.id.set_reminder_tv_week_name)
    TextView tv_week_name;
    @BindView(R.id.set_reminder_tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.set_reminder_tv_stop_time)
    TextView tv_stop_time;
    private MyAdapter myAdapter;
    private String[] weekday=new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    private List<LiteWeekBean> liteWeekBeanList=new ArrayList<>();
    private  String WeekName="";
    private String WeekCode;
    private List<String> HourList=new ArrayList<>();
    private List<String> MinList=new ArrayList<>();
    private Context mContext;
    private String Hour="00";
    private String Min="00";
    private String token;
    private SetReminderPresenter setReminderPresenter;
    private CustomProgressDialog progressDialog;
    private List<LiteWeekBean> liteWeekBeans=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        initData();
        InitUI();
        InitData();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        setReminderPresenter = new SetReminderPresenter(this);
        setReminderPresenter.GetclockRemindedInfo(token);
    }

    private void InitData() {
        try {
            LitePal.deleteAll(LiteWeekBean.class);
            for (int a = 0; a < 7; a++) {
                LiteWeekBean liteWeekBean = new LiteWeekBean();
                liteWeekBean.setSlt_code(0);
                liteWeekBean.setWeek_code(a);
                liteWeekBean.setWeek_name(weekday[a]);
                liteWeekBean.save();
            }
            liteWeekBeanList = LitePal.findAll(LiteWeekBean.class);
            myAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }
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

    private void InitUI() {
        myAdapter = new MyAdapter();
        listView_week.setAdapter(myAdapter);
    }

    @OnClick({R.id.set_reminder_img_back,R.id.set_reminder_btn_week_confirm,R.id.set_reminder_rl_sltweek,
    R.id.set_reminder_rl_start_time,R.id.set_reminder_rl_stop_time,R.id.set_reminder_btn_save})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.set_reminder_img_back:
                finish();
                break;
            case R.id.set_reminder_btn_week_confirm:
                WeekName="";
                WeekCode="";
                liteWeekBeans = LitePal.where("slt_code=1").find(LiteWeekBean.class);
                for (int a = 0; a< liteWeekBeans.size(); a++){
                    if (a==0){
                        WeekName= liteWeekBeans.get(a).getWeek_name();
                        WeekCode=""+ liteWeekBeans.get(a).getWeek_code();
                    }else {
                        WeekName=WeekName+","+ liteWeekBeans.get(a).getWeek_name();
                        WeekCode=WeekCode+","+ liteWeekBeans.get(a).getWeek_code();
                    }
                }
                tv_week_name.setText(WeekName);
                rl_dialog.setVisibility(View.GONE);
                break;
            case R.id.set_reminder_rl_sltweek:
                rl_dialog.setVisibility(View.VISIBLE);
                break;
            case R.id.set_reminder_rl_start_time:
                BirthDateDialog(0);
                break;
            case R.id.set_reminder_rl_stop_time:
                BirthDateDialog(1);
                break;
            case R.id.set_reminder_btn_save:
                String startTime= tv_start_time.getText().toString();
                String stopTimes = tv_stop_time.getText().toString();
                setReminderPresenter.GetClockReminded(token,startTime,stopTimes,WeekCode);
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

    public class  MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return liteWeekBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.list_set_reminde_week, null);
            CheckBox remind_cb = inflate.findViewById(R.id.list_set_remind_cb);
            TextView tv_weekname= inflate.findViewById(R.id.list_set_remind_tv_weekname);
            tv_weekname.setText(liteWeekBeanList.get(i).getWeek_name());
            if (liteWeekBeanList.get(i).getSlt_code()==1){
                remind_cb.setChecked(true);
            }else {
                remind_cb.setChecked(false);
            }
            remind_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    LiteWeekBean liteWeekBean = new LiteWeekBean();
                    if (isChecked) {
                        liteWeekBean.setSlt_code(1);
                        liteWeekBean.updateAll("week_code="+ i);
                    }else {
                        liteWeekBean.setSlt_code(2);
                        liteWeekBean.updateAll("week_code="+ i);
                    }
                    liteWeekBeans = LitePal.where("slt_code=1").find(LiteWeekBean.class);
                    Log.e("jkdnfjsd", liteWeekBeans.size()+"jdfhjsf");

                }
            });
            return inflate;
        }
    }

    //上下班时间
    private void BirthDateDialog(int type){
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_birth_date, null);
        TextView tv_title = BirthDateInflate.findViewById(R.id.dialog_bottom_tv_title);
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
                    tv_start_time.setText(Hour+":"+Min);
                }else if (type==1){
                    tv_stop_time.setText(Hour+":"+Min);
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

    //更新数据
    public void UpdateUI(int code,String msg){
        if (code==1){
            finish();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }

    //页面刷新
    public void UpdateUI(int code,String msg, ClockRemindedInfoBean.DataBean data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1){
            String repetition = data.getRepetition();
            String[] split = repetition.split(",");
            for (int a=0;a<split.length;a++){
                LiteWeekBean liteWeekBean = new LiteWeekBean();
                liteWeekBean.setSlt_code(1);
                liteWeekBean.updateAll("week_code= "+split[a]);
            }
            List<LiteWeekBean> LiteWeekBean = LitePal.where("slt_code=1").find(LiteWeekBean.class);
            for (int a=0;a<LiteWeekBean.size();a++){
                if (a==0){
                    WeekName=LiteWeekBean.get(a).getWeek_name();
                    WeekCode=""+LiteWeekBean.get(a).getWeek_code();
                }else {
                    WeekName=WeekName+","+LiteWeekBean.get(a).getWeek_name();
                    WeekCode=WeekCode+","+LiteWeekBean.get(a).getWeek_code();
                }
            }
            tv_week_name.setText(WeekName);
            tv_start_time.setText(data.getStart_time());
            tv_stop_time.setText(data.getEnd_time());
            liteWeekBeanList = LitePal.findAll(LiteWeekBean.class);
            myAdapter.notifyDataSetChanged();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }
}
