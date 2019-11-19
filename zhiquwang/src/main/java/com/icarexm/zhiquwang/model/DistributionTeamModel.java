package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.DistributionTeamContract;
import com.icarexm.zhiquwang.presenter.DistributionTeamPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class DistributionTeamModel implements DistributionTeamContract.Model {

    public void PostTeam(DistributionTeamPresenter distributionTeam,String token){
        OkGo.<String>post(RequstUrl.URL.TeamInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                  distributionTeam.SetTeam(response.body());
                    }
                });
    }
}
