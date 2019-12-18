package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PlayInfoBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.calender.DateUtil;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.AttendanceCardActivity;
import com.icarexm.zhiquwang.view.activity.HomeActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.PunchCardRecordActivity;
import com.icarexm.zhiquwang.view.activity.SetReminderActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockInFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private String token;
    private RelativeLayout rl_entry;
    private RelativeLayout rl_no_entry;
    private TextView tv_certification;
    private TextView tv_set_reminder;
    private String todayDate;
    private String todayHour;
    private String week;
    private TextView tv_date;
    private TextView tv_time;
    private TextView tv_clockInDt;
    private TextView tv_clockOutDt;
    private String todaySecond;
    private RelativeLayout ll_playStatus;
    private TextView tv_start_time;
    private TextView tv_end_time;
    private double latitude;
    private double longitude;
    private String cityName;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    latitude = aMapLocation.getLatitude();
                    //获取经度
                    longitude = aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    //城市信息
                    cityName = aMapLocation.getCity();
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("cityName",cityName);
                    editor.putString("latitude",latitude+"");
                    editor.putString("longitude",longitude+"");
                    editor.commit();//提交
                }
            }
        }
    };
    AMapLocationClientOption option = new AMapLocationClientOption();
    private AMapLocationClientOption mLocationOption;
    private SharedPreferences share;
    private AMapLocationClient mLocationClient;
    private RelativeLayout rl_clockin;
    private RelativeLayout rl_clockOut;
    private View driver;
    private TextView tv_address;
    private CustomProgressDialog progressDialog;

    public ClockInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        View inflate = inflater.inflate(R.layout.fragment_clock_in, container, false);
        todayDate = DateUtils.getTodayDate();
        week = DateUtils.getWeek(todayDate);
        todayHour = DateUtils.getTodayHour();
        startLocation();
        InitUI(inflate);
        InitData();
        return inflate;
    }

    private void InitData() {
        LoadingDialogShow();
        OkGo.<String>post(RequstUrl.URL.playInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      LoadingDialogClose();
                        PlayInfoBean playInfoBean = new GsonBuilder().create().fromJson(response.body(), PlayInfoBean.class);
                        if (playInfoBean.getCode()==1){
                            PlayInfoBean.DataBean data = playInfoBean.getData();
                            rl_no_entry.setVisibility(View.GONE);
                            tv_set_reminder.setVisibility(View.VISIBLE);
                            rl_entry.setVisibility(View.VISIBLE);
                            tv_address.setText(data.getAddress()+"");
                            if (data.getIs_auth()==2){
                                tv_certification.setVisibility(View.GONE);
                                int start_status = data.getStart_status();
                                int end_status = data.getEnd_status();
                                if (start_status==2){
                                    ll_playStatus.setVisibility(View.VISIBLE);
                                    rl_clockin.setVisibility(View.GONE);
                                    driver.setVisibility(View.GONE);
                                    tv_start_time.setText("打卡时间  "+data.getStart_time()+"（上班打卡）");
                                    rl_clockOut.setBackgroundResource(R.drawable.bg_circle_green);
                                }else {
                                    rl_clockOut.setClickable(false);
                                    ll_playStatus.setVisibility(View.GONE);
                                }
                                if (end_status==2){
                                    rl_clockOut.setVisibility(View.GONE);
                                    tv_end_time.setText("打卡时间 "+data.getEnd_time()+"（下班打卡）");
                                }else {
                                    tv_end_time.setText("还未打卡 "+data.getEnd_time()+"（下班打卡）");
                                }
                            }else {
                                tv_certification.setVisibility(View.VISIBLE);
                            }
                        }else if (playInfoBean.getCode()==20002){
                            rl_entry.setVisibility(View.GONE);
                            tv_set_reminder.setVisibility(View.GONE);
                            rl_no_entry.setVisibility(View.VISIBLE);
                        }else if (playInfoBean.getCode() ==10001){
                            try {
                                startActivity(new Intent(mContext, LoginActivity.class));
                                getActivity().finish();
                            }catch (Exception e){

                            }
                        }
                    }
                });
    }

    private void InitUI(View inflate) {
        //为实名显示
        tv_certification = inflate.findViewById(R.id.fm_clock_in_tv_certification);
        rl_no_entry = inflate.findViewById(R.id.fm_clock_in_rl_no_entry);
        rl_entry = inflate.findViewById(R.id.fm_clock_in_rl_entry);
        tv_set_reminder = inflate.findViewById(R.id.fm_clock_in_set_reminder);
        ll_playStatus = inflate.findViewById(R.id.clock_in_ll_playStats);
        tv_start_time = inflate.findViewById(R.id.clock_in_tv_start_time);
        tv_end_time = inflate.findViewById(R.id.clock_in_tv_end_time);
        driver = inflate.findViewById(R.id.fm_clockin_driver);
        tv_address = inflate.findViewById(R.id.fm_clock_in_tv_address);
        inflate.findViewById(R.id.fm_clock_in_no_entry).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_rl_attendance).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_punch_card_record).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_set_reminder).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clcokin_tv_start_recruit).setOnClickListener(this);
        rl_clockin = inflate.findViewById(R.id.fm_clockin_rl_clockin);
        rl_clockin.setOnClickListener(this);
        rl_clockOut = inflate.findViewById(R.id.fm_clockin_rl_clockOut);
        rl_clockOut.setOnClickListener(this);
        tv_date = inflate.findViewById(R.id.fm_clock_in_tv_date);
        tv_time = inflate.findViewById(R.id.fm_clock_in_tv_time);
        tv_date.setText(todayDate +"  "+week);
        tv_time.setText(todayHour);
        tv_clockInDt = inflate.findViewById(R.id.fm_clconin_tv_clockInDt);
        tv_clockOutDt = inflate.findViewById(R.id.fm_clcokin_tv_clockOutDt);
        String todaySecond = DateUtils.getTodaySecond();
        tv_clockInDt.setText(todaySecond);
        todaySecond = DateUtils.getTodaySecond();
        tv_clockInDt.setText(todaySecond);
        tv_clockOutDt.setText(todaySecond);
        if (TimeRunnable!=null){
            TimeHandle.postDelayed(TimeRunnable,1000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_clock_in_rl_attendance:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clock_in_rl_attendance)) {
                    startActivity(new Intent(mContext, AttendanceCardActivity.class));
                }
                break;
            case R.id.fm_clock_in_punch_card_record:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clock_in_punch_card_record)) {
                    startActivity(new Intent(mContext, PunchCardRecordActivity.class));
                }
                break;
            case R.id.fm_clock_in_set_reminder:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clock_in_set_reminder)) {
                    startActivity(new Intent(mContext, SetReminderActivity.class));
                }
                break;
            case R.id.fm_clock_in_no_entry:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clock_in_no_entry)) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                }
                break;
            case R.id.fm_clockin_rl_clockin:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clockin_rl_clockin)) {
                    ToPlay("1");
                }
                break;
            case R.id.fm_clockin_rl_clockOut:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clockin_rl_clockOut)) {
                    ToPlay("2");
                }
                break;
            case R.id.fm_clcokin_tv_start_recruit:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_clcokin_tv_start_recruit)) {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    intent.putExtra("currentItems", "0");
                    startActivity(intent);
                }
                break;
        }
    }

    private Handler TimeHandle=new Handler();
    Runnable TimeRunnable=new Runnable() {
        @Override
        public void run() {
            todayHour = DateUtils.getTodayHour();
            tv_time.setText(todayHour);
            todaySecond = DateUtils.getTodaySecond();
            tv_clockInDt.setText(todaySecond);
            tv_clockOutDt.setText(todaySecond);
            TimeHandle.postDelayed(TimeRunnable,5000);
        }
    };

    public void  ToPlay(String type){
        OkGo.<String>post(RequstUrl.URL.ToPlay)
                .params("token",token)
                .params("latitude",latitude)
                .params("longitude",longitude)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicCodeBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicCodeBean.class);
                        ToastUtils.showToast(mContext,resultBean.getMsg());
                        if (resultBean.getCode()==1){
                            InitData();
                        }else if (resultBean.getCode() ==10001){
                            try {
                                startActivity(new Intent(mContext, LoginActivity.class));
                                getActivity().finish();
                            }catch (Exception e){

                            }
                        }
                    }
                });
    }

    private void startLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null != mLocationClient){
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //获取一次定位结果：
        mLocationOption.setOnceLocation(false);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒
        mLocationOption.setInterval(3000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    public void UpdateUI(){
        InitData();
    }

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(mContext);
            }
            progressDialog.show();
        }catch (Exception e){

        }
    }

    //关闭刷新
    public void LoadingDialogClose(){
        try {
            if (progressDialog != null){
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch (Exception e){

        }

    }


}
