package com.icarexm.zhiquwang.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.ShadowDrawable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinFragment extends Fragment {


    public MinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_min, container, false);
        InitUI();
        return inflate;
    }

    private void InitUI() {
        new ShadowDrawable.Builder()
                .setShapeRadius(R.dimen.dp_61)
                .setShadowColor(R.color.FFF6FBFD)
                .setShadowRadius(0)
                .setOffsetX(R.dimen.dp_5)
                .setOffsetY(R.dimen.dp_10)
                .builder();

    }

}
