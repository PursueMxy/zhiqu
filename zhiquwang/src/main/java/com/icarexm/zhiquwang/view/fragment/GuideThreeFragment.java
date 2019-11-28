package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideThreeFragment extends Fragment {

    private SharedPreferences share;

    public static GuideThreeFragment newInstance() {

        return new GuideThreeFragment();
    }


    public GuideThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context mContext = getContext();
        View inflate = inflater.inflate(R.layout.fragment_guide_three, container, false);
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        inflate.findViewById(R.id.guide_three_btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("bootpage","true");
                editor.commit();
                getActivity().finish();
            }
        });
        return inflate;
    }

}
