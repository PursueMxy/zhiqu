package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyTeamContract;
import com.icarexm.zhiquwang.presenter.MyTeamPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyTeamModel implements MyTeamContract.Model {

    /**
     * 我的团队
     */
    public void PostMyTeam(MyTeamPresenter myTeamPresenter,String token, String limit, String page){
        OkGo.<String>post(RequstUrl.URL.MyTeam)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     myTeamPresenter.SetMyTeam(response.body());
                    }
                });
    }
}
