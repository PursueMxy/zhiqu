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
public class RecordOvertimeFragment extends Fragment {


    public RecordOvertimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_record_overtime, container, false);
        return inflate;
    }

}
