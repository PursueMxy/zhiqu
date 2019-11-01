package com.icarexm.zhiquwang.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DistributionTeamActivity extends BaseActivity {


    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_team);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.distribution_team_rl_team,R.id.distribution_team_img_back,R.id.distribution_team_rl_commission,R.id.distribution_team_rl_poster})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.distribution_team_rl_team:
                startActivity(new Intent(mContext,MyTeamActivity.class));
                break;
            case R.id.distribution_team_img_back:
                finish();
                break;
            case R.id.distribution_team_rl_commission:
                startActivity(new Intent(mContext,CommissionActivity.class));
                break;
            case R.id.distribution_team_rl_poster:
                startActivity(new Intent(mContext,DistributionPosterActivity.class));
                break;
        }
    }
}
