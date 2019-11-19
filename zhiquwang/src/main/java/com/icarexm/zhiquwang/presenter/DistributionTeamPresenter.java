package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.TeamBean;
import com.icarexm.zhiquwang.contract.DistributionTeamContract;
import com.icarexm.zhiquwang.model.DistributionTeamModel;

public class DistributionTeamPresenter implements DistributionTeamContract.Presenter {

    public DistributionTeamContract.View mView;
    public DistributionTeamModel distributionTeamModel;
    public DistributionTeamPresenter(DistributionTeamContract.View view){
        this.mView=view;
        distributionTeamModel=new DistributionTeamModel();
    }

    public void GetTeam(String token){
    distributionTeamModel.PostTeam(this,token);
    }

    //
    public void SetTeam(String content){
        TeamBean teamBean = new GsonBuilder().create().fromJson(content, TeamBean.class);
        mView.UpdateUI(teamBean.getCode(),teamBean.getMsg(),teamBean.getData());
    }
}
