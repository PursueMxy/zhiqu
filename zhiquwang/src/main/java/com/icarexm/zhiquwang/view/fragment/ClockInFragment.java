package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.view.activity.AttendanceCardActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockInFragment extends Fragment implements View.OnClickListener {


    private Context mContext;

    public ClockInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_clock_in, container, false);
        mContext = getContext();
        InitUI(inflate);
        return inflate;
    }

    private void InitUI(View inflate) {
        inflate.findViewById(R.id.fm_clock_in_rl_attendance).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_clock_in_rl_attendance:
                startActivity(new Intent(mContext, AttendanceCardActivity.class));
                break;
        }
    }
}
