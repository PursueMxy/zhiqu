package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.view.activity.AttendanceCardActivity;
import com.icarexm.zhiquwang.view.activity.HomeActivity;
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
    private RelativeLayout rl_certification;
    private TextView tv_set_reminder;

    public ClockInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        SharedPreferences share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        View inflate = inflater.inflate(R.layout.fragment_clock_in, container, false);
        InitUI(inflate);
        InitData();
        return inflate;
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.playInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                        if (resultBean.getCode()==1){
                            rl_no_entry.setVisibility(View.GONE);
                            tv_set_reminder.setText(View.VISIBLE);
                            rl_entry.setVisibility(View.VISIBLE);
                        }else if (resultBean.getCode()==20002){
                            rl_entry.setVisibility(View.GONE);
                            tv_set_reminder.setVisibility(View.GONE);
                            rl_no_entry.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void InitUI(View inflate) {
        //为实名显示
        rl_certification = inflate.findViewById(R.id.fm_clock_in_rl_certification);
        rl_no_entry = inflate.findViewById(R.id.fm_clock_in_rl_no_entry);
        rl_entry = inflate.findViewById(R.id.fm_clock_in_rl_entry);
        tv_set_reminder = inflate.findViewById(R.id.fm_clock_in_set_reminder);
        inflate.findViewById(R.id.fm_clock_in_no_entry).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_rl_attendance).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_punch_card_record).setOnClickListener(this);
        inflate.findViewById(R.id.fm_clock_in_set_reminder).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_clock_in_rl_attendance:
                startActivity(new Intent(mContext, AttendanceCardActivity.class));
                break;
            case R.id.fm_clock_in_punch_card_record:
                startActivity(new Intent(mContext, PunchCardRecordActivity.class));
                break;
            case R.id.fm_clock_in_set_reminder:
                startActivity(new Intent(mContext, SetReminderActivity.class));
                break;
            case R.id.fm_clock_in_no_entry:
               startActivity(new Intent(mContext, HomeActivity.class));
                break;
        }
    }
}
