package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.CommissionContract;
import com.icarexm.zhiquwang.presenter.CommissionPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CommissionModel implements CommissionContract.Model {
    /**
     * 获取佣金列表/提现列表/奖励列表
     * @param type 1 提现列表 2 佣金列表 3 推荐列表 4 邀请
     */
    public void PostSeeFund(CommissionPresenter CommissionPresenter, String token, String type){

        OkGo.<String>post(RequstUrl.URL.SeeFund)
                .params("token",token)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CommissionPresenter.SetSeeFund(response.body(),type);
                    }
                });

    }
}
