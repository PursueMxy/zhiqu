package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.view.activity.BaseActivity;
import com.icarexm.zhiquwang.view.activity.BasePayActivity;
import com.icarexm.zhiquwang.view.activity.OvertimeApprovalActivity;
import com.icarexm.zhiquwang.view.activity.OvertimeStatisticsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordOvertimeFragment extends Fragment implements View.OnClickListener {


    private Context mContext;
    private RelativeLayout rl_typeOne;
    private RelativeLayout rl_typeTwo;
    private String TypeOfWork="2";

    public RecordOvertimeFragment() {
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
        View inflate = inflater.inflate(R.layout.fragment_record_overtime, container, false);
        InitUI(inflate);
        return inflate;
    }

    private void InitUI(View inflate) {
        rl_typeOne = inflate.findViewById(R.id.fm_recordOvertime_rl_TypeOne);
        rl_typeTwo = inflate.findViewById(R.id.fm_recordOvertime_rl_TypeTwo);
        inflate.findViewById(R.id.fm_record_overtime_tv_set_basepay).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_two_tv_set_basepay).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_statistics).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_approval).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_two_tv_statistics).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_two_tv_approval).setOnClickListener(this);
        if (TypeOfWork.equals("1")){
            rl_typeTwo.setVisibility(View.GONE);
            rl_typeOne.setVisibility(View.VISIBLE);
        }else {
            rl_typeOne.setVisibility(View.GONE);
            rl_typeTwo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_record_overtime_tv_set_basepay:
                Intent intent4 = new Intent(mContext, BasePayActivity.class);
                intent4.putExtra("Base_Type","1");
                startActivity(intent4);
                break;
            case R.id.fm_record_overtime_two_tv_set_basepay:
                Intent intent5 = new Intent(mContext, BasePayActivity.class);
                intent5.putExtra("Base_Type","2");
                startActivity(intent5);
                break;
            case R.id.fm_record_overtime_overtime_statistics:
                Intent intent = new Intent(mContext, OvertimeStatisticsActivity.class);
                intent.putExtra("TypeOfWork","1");
                startActivity(intent);
                break;
            case R.id.fm_record_overtime_overtime_approval:
                Intent intent1 = new Intent(mContext, OvertimeApprovalActivity.class);
                intent1.putExtra("TypeOfWork","1");
                startActivity(intent1);
                break;
            case R.id.fm_record_overtime_overtime_two_tv_approval:
                Intent intent2 = new Intent(mContext, OvertimeApprovalActivity.class);
                intent2.putExtra("TypeOfWork","2");
                startActivity(intent2);
                break;
            case R.id.fm_record_overtime_overtime_two_tv_statistics:
                Intent intent3 = new Intent(mContext, OvertimeStatisticsActivity.class);
                intent3.putExtra("TypeOfWork","2");
                startActivity(intent3);
                break;
        }
    }
}
