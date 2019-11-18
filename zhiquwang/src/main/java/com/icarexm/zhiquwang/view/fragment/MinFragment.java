package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.ShadowDrawable;
import com.icarexm.zhiquwang.view.activity.BaseInformationActivity;
import com.icarexm.zhiquwang.view.activity.BusinessCooperationActivity;
import com.icarexm.zhiquwang.view.activity.CertificationActivity;
import com.icarexm.zhiquwang.view.activity.DistributionTeamActivity;
import com.icarexm.zhiquwang.view.activity.EditPersonalActivity;
import com.icarexm.zhiquwang.view.activity.MyJobSearchActivity;
import com.icarexm.zhiquwang.view.activity.MyResumeActivity;
import com.icarexm.zhiquwang.view.activity.MyTeamActivity;
import com.icarexm.zhiquwang.view.activity.MyToJoinActivity;
import com.icarexm.zhiquwang.view.activity.MyWalletActivity;
import com.icarexm.zhiquwang.view.activity.SetActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinFragment extends Fragment implements View.OnClickListener {


    private Context mContext;

    public MinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        View inflate = inflater.inflate(R.layout.fragment_min, container, false);
        InitUI(inflate);
        return inflate;
    }

    private void InitUI(View inflate) {
      inflate.findViewById(R.id.fm_min_rl_myjobsearch).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_myteam).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_img_edit).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_waller).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_set).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_resume).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_certification).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_to_join).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_businessCooperation).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_min_rl_myjobsearch:
                startActivity(new Intent(mContext, MyJobSearchActivity.class));
                break;
            case R.id.fm_min_rl_myteam:
                startActivity(new Intent(mContext, DistributionTeamActivity.class));
                break;
            case R.id.fm_min_img_edit:
                startActivity(new Intent(mContext, BaseInformationActivity.class));
                break;
            case R.id.fm_min_rl_waller:
                startActivity(new Intent(mContext, MyWalletActivity.class));
                break;
            case R.id.fm_min_rl_set:
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.fm_min_rl_resume:
                startActivity(new Intent(mContext, MyResumeActivity.class));
                break;
            case R.id.fm_min_rl_certification:
                startActivity(new Intent(mContext, CertificationActivity.class));
                break;
            case R.id.fm_min_rl_to_join:
                startActivity(new Intent(mContext, MyToJoinActivity.class));
                break;
            case R.id.fm_min_rl_businessCooperation:
                startActivity(new Intent(mContext, BusinessCooperationActivity.class));
                break;
        }
    }
}
