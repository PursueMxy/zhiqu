package com.icarexm.zhiquwang.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClockInFragment extends Fragment {


    public ClockInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_clock_in, container, false);
        return inflate;
    }

}
