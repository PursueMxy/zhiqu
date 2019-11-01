package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.view.activity.BaseActivity;
import com.icarexm.zhiquwang.view.activity.BasePayActivity;
import com.icarexm.zhiquwang.view.activity.OvertimeStatisticsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordOvertimeFragment extends Fragment implements View.OnClickListener {


    private Context mContext;

    public RecordOvertimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View inflate = inflater.inflate(R.layout.fragment_record_overtime, container, false);
        InitUI(inflate);
        return inflate;
    }

    private void InitUI(View inflate) {
        inflate.findViewById(R.id.fm_record_overtime_tv_set_basepay).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_statistics).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_record_overtime_tv_set_basepay:
                startActivity(new Intent(mContext, BasePayActivity.class));
                break;
            case R.id.fm_record_overtime_overtime_statistics:
                startActivity(new Intent(mContext, OvertimeStatisticsActivity.class));
                break;
        }
    }
}
