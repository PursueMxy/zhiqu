package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.BasePayContract;
import com.icarexm.zhiquwang.presenter.BasePayPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BasePayModel implements BasePayContract.Model {

    /**
     * 工资设置页面初始化
     */
    public void PostOvertimeInfo(BasePayPresenter basePayPresenter,String token,String type){
        OkGo.<String>post(RequstUrl.URL.OvertimeInfo)
                .params("token",token)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

}
